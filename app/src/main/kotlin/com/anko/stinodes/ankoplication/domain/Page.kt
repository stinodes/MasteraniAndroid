package com.anko.stinodes.ankoplication.domain

import com.google.gson.annotations.SerializedName

class Page<T> {
    var total: Int? = null
    var data: List<T>? = null
    @SerializedName("per_page") var perPage: Int? = null
    @SerializedName("current_page") var currentPage: Int? = null
    @SerializedName("last_page") var lastPage: Int? = null
}