package com.anko.stinodes.ankoplication.domain

import android.os.Parcel
import android.os.Parcelable

class EpisodeParcelable: Parcelable {
    var animeId: Int? = null
    var episode: Int? = null

    constructor(animeId: Int?, episode: Int?) {
        this.animeId = animeId
        this.episode = episode
    }
    constructor(source: Parcel?) {
        if (source != null) {
            animeId = source.readInt()
            episode = source.readInt()
        }
    }

    companion object {
        val CREATOR = object: Parcelable.Creator<EpisodeParcelable> {
            override fun createFromParcel(source: Parcel?)
                    : EpisodeParcelable = EpisodeParcelable(source)

            override fun newArray(size: Int)
                    : Array<EpisodeParcelable?>
                    = arrayOfNulls<EpisodeParcelable?>(size)
        }

        fun fromRelease(release: Release) = EpisodeParcelable(release.anime?.id, release.episode)
        fun fromId(id: Int) = EpisodeParcelable(id, null)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        if (animeId != null) dest.writeInt(animeId!!)
        if (episode != null) dest.writeInt(episode!!)
    }

    override fun describeContents(): Int {
        return 0
    }
}