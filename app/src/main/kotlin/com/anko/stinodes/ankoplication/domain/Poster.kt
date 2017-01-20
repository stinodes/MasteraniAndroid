package com.anko.stinodes.ankoplication.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Poster: RealmObject() {
    @PrimaryKey open var id: String? = null
    open var path: String = "poster/"
    open var extension: String = "jpg"
    open var file: String? = null
}