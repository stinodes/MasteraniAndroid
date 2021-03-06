package com.anko.stinodes.ankoplication.domain.detailedanime

import com.google.gson.annotations.SerializedName
import java.util.*

class DetailedAnimeInfo {
    var id: Int? = null
    var title: String? = null
    var slug: String? = null
    var synopsis: String? = null
    var score: Float? = null
    @SerializedName("episode_count") var episodeCount: Int? = null
    @SerializedName("episode_length") var episodeLength: Int? = null
    @SerializedName("started_airing_date") var startedAiringDate: Date? = null
    @SerializedName("finished_airing_date") var finishedAiringDate: Date? = null
    @SerializedName("wallpaper_id") var wallpaperId: String? = null

    fun scoreToString(): String =
            if (score != null)
                "${score} / 5"
            else
                "? / 5"

    fun episodesToString(): String =
            if (episodeCount != null && episodeLength != null)
                "${episodeCount} eps of ${episodeLength} min."
            else if (episodeCount != null)
                "${episodeCount} eps"
            else if (episodeLength != null)
                "eps of ${episodeLength} min."
            else "Not available."

    fun dateToString(): String {
        if (startedAiringDate == null)
            return "-"
        val cal = Calendar.getInstance()
        var start: Int
        var finish: Int

        cal.time = startedAiringDate
        start = cal.get(Calendar.YEAR)

        if (finishedAiringDate == null)
            return "$start - ?"

        cal.time = finishedAiringDate
        finish = cal.get(Calendar.YEAR)

        if (start == finish)
            return "$start"

        return "$start - $finish"
    }
}