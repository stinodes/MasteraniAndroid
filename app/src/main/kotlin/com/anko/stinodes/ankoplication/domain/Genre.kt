package com.anko.stinodes.ankoplication.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Genre: RealmObject() {
    @PrimaryKey open var id: Int? = null
    open var name: String? = null
}