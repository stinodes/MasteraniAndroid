package com.anko.stinodes.ankoplication.web

import android.util.Log
import com.anko.stinodes.ankoplication.domain.*
import io.realm.Realm
import io.realm.RealmModel
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates

class MAWrapper {

    /**
     * Singleton stuff.
     */
    companion object {
        private var instance: MAWrapper? = null

        fun get(): MAWrapper {
            if (instance == null)
                instance = MAWrapper()
            return instance!!
        }
    }

    var realm: Realm by Delegates.notNull()
    var api: MAService by Delegates.notNull()
    enum class Order(val str: String) {
        TITLE_ASC("title"),
        TITLE_DESC("title_desc"),
        SCORE_ASC("score"),
        SCORE_DESC("score_desc")
    }

    init {
        realm = Realm.getDefaultInstance()
        api = createMAService()
    }

    fun onSuccessfulRealmTransaction() {
        Log.d("REALM", "SUCCESSFULLY WROTE TO REALM")
    }
    fun onFailedRealmTransaction(throwable: Throwable) {
        throwable.printStackTrace()
    }

    fun onSuccessfulAPIRequest() {

    }
    fun onFailedAPIRequest(throwable: Throwable) {
        throwable.printStackTrace()
    }

    fun getPref(key: String): String {
        val pref = realm.where(Pref::class.java)
                .equalTo("key", key)
                .findFirst()
        if (pref != null && pref.value != null)
            return pref.value!!
        return ""
    }
    fun setPref(key: String, value: String) {
        val pref = Pref()
        pref.key = key
        pref.value = value
        realm.executeTransaction {
            Log.d("PREF", "Setting pref ${pref.key} to ${pref.value}")
            it.copyToRealmOrUpdate(pref)
        }
    }

    /**
     * Returns an observable that emits a list of releases.
     */
    fun releasesObservable()
            : Observable<List<Release>>
            = api.getReleases()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

    /**
     * Returns observable that emits a page of anime
     */
    fun animeObservable(page: Int = 1, order: Order = Order.SCORE_DESC, genres: List<Int> = listOf())
            : Observable<Page<Anime>>
            = api.getAnime(page, order.str, genres.joinToString(","))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())


    /**
     * Fetches releases and saves to Realm DB.
     * Dynamic Realm results will be able to resolve
     * changes to list
     */
    fun fetchReleases() {
        releasesObservable()
                .subscribe(
                        { releases ->
                            onSuccessfulAPIRequest()
                            persistReleases(releases)
                        },
                        { onFailedAPIRequest(it) }
                )
    }
    /**
     * Fetches anime for a given page and saves to Realm DB.
     * Dynamic Realm results will be able to resolve
     * changes to list
     */
    fun fetchAnime(page: Int = 1, order: Order = Order.SCORE_ASC, genres: List<Int> = listOf()) {
        animeObservable(page, order, genres)
                .subscribe({ pageObj ->
                    onSuccessfulAPIRequest()
                    persistAnime(pageObj.data!!)
                },
                        { onFailedAPIRequest(it) })
    }

    /**
     * Persist releases to Realm DB.
     * Passes a function to delete already existing
     * entries, since there is no primary key.
     */
    fun persistReleases(releases: List<Release>) {
        fun beforeCopy(data: List<Release>, bgRealm: Realm) = data.map(
                {
                    bgRealm.where(Release::class.java)
                            .equalTo("anime.id", it.anime?.id)
                            .equalTo("episode", it.episode)
                            .findAll()
                            .deleteAllFromRealm()
                    it
                }
        )
        persist(releases, ::beforeCopy)
    }
    /**
     * Persists anime.
     */
    fun persistAnime(anime: List<Anime>) {
        persist(anime)
    }

    /**
     * Persists genres (usually on app startup)
     */
    fun persistGenres(genres: List<Genre>) {
        persist(genres)
    }


    fun <T : List<RealmModel>>persist(data: T, beforeCopy: ((T, Realm) -> T)? = null) {
        realm.executeTransactionAsync(
                { bgRealm ->
                    val newData = if (beforeCopy != null) beforeCopy(data, bgRealm) else data
                    bgRealm.copyToRealmOrUpdate(newData)
                },
                { onSuccessfulRealmTransaction() },
                { onFailedRealmTransaction(it) }
        )
    }
    fun <T : RealmModel>persist(data: T) {
        persist(listOf(data))
    }
}