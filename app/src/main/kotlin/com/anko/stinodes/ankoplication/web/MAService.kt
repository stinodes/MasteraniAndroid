package com.anko.stinodes.ankoplication.web

import com.anko.stinodes.ankoplication.domain.Anime
import com.anko.stinodes.ankoplication.domain.Page
import com.anko.stinodes.ankoplication.domain.Release
import com.anko.stinodes.ankoplication.domain.detailedanime.DetailedAnime
import retrofit2.http.*
import rx.Observable

interface MAService {
    companion object {
        val LOGIN_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8"
    }

    @GET("api/releases")
    fun getReleases(): Observable<List<Release>>

    @GET("api/anime/filter")
    fun getAllAnime(
            @Query("page") page: Int,
            @Query("order") order: String,
            @Query("genres") genres: String
    ): Observable<Page<Anime>>

    @GET("api/anime/{slug}/detailed")
    fun getDetailedAnimeSlug(@Path("slug") slug: String): Observable<DetailedAnime>

    @GET("api/anime/{id}/detailed")
    fun getDetailedAnimeId(@Path("id") id: Int): Observable<DetailedAnime>

    @POST("auth/sign-in")
    fun login(
            @Header("Content-Type") contentType: String = LOGIN_CONTENT_TYPE,
            @Body credentials: String
    ): Observable<Unit>

}