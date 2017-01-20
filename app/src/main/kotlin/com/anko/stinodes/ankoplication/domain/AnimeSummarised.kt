package com.anko.stinodes.ankoplication.domain

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

open class AnimeSummarised: RealmObject() {

    @PrimaryKey @Index open var id: Int? = null
    open var title: String? = null
    open var slug: String? = null
    open var duration: Int? = null
    open var age: String? = null
    open var poster: String? = null
    open var wallpaper: String? = null

}