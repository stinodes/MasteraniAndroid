package com.anko.stinodes.ankoplication.domain

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Index

open class Release: RealmObject() {

    open var anime: AnimeSummarised? = null
    @Index open var episode: Int? = null
    @SerializedName("created_at") open var createdAt: String? = null

}