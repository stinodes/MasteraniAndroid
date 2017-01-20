package com.anko.stinodes.ankoplication.domain

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Anime: RealmObject() {

    @PrimaryKey open var id: Int? = null
    open var title: String? = null
    open var slug: String? = null
    open var status: String? = null
    open var type: Int? = null
    open var score: Float? = null
    open var genres: RealmList<Genre>? = null
    open var poster: Poster? = null
    @SerializedName("episode_count") open var epCount: Int? = null
    @SerializedName("started_airing_date") open var startedAiring: Date? = null
    @SerializedName("finished_airing_date") open var finishedAiring: Date? = null

}