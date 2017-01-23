package com.anko.stinodes.ankoplication.domain.detailedanime

import com.anko.stinodes.ankoplication.domain.Genre

open class DetailedAnime {
    open var info: DetailedAnimeInfo? = null
    open var genres: List<Genre>? = null
    open var poster: String? = null
    open var wallpapers: List<Wallpaper>? = null
    open var episodes: List<Episode>? = null
}