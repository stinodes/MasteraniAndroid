package com.anko.stinodes.ankoplication.domain

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Pref: RealmObject() {
    @PrimaryKey open var key: String? = null
    open var value: String? = null
}