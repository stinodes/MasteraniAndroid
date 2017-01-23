package com.anko.stinodes.ankoplication.domain.detailedanime

import com.google.gson.annotations.SerializedName

class Episode {
    var info: EpisodeInfo? = null
    var thumbnail: String? = null
}

class EpisodeInfo {
    var id: Int? = null
    @SerializedName("anime_id") var animeId: Int? = null
    var episode: Int? = null
    var title: String? = null
    var description: String? = null

}