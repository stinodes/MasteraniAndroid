package com.anko.stinodes.ankoplication

import android.app.Application
import android.util.Log
import com.anko.stinodes.ankoplication.domain.Genre
import com.anko.stinodes.ankoplication.util.loadJson
import com.anko.stinodes.ankoplication.web.MAWrapper
import com.google.gson.Gson
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlin.properties.Delegates



class Application : Application() {

    var realm: Realm by Delegates.notNull<Realm>()
    var maWrapper: MAWrapper by Delegates.notNull<MAWrapper>()

    override fun onCreate() {

        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)

        // Setup MAWrapper to handle api interactions and db writes.
        maWrapper = MAWrapper.get()
        if (maWrapper.getPref("isNotFirstOpen") == "")
            maWrapper.persistGenres(
                    listOf(
                            *Gson().fromJson(
                                    loadJson(baseContext, "json/genres.json"),
                                    Array<Genre>::class.java
                            )
                    )
            )
        else
            Log.d("APP", "Genres already set")
        maWrapper.setPref("isNotFirstOpen", "true")
        maWrapper.fetchReleases()
        super.onCreate()
    }
}