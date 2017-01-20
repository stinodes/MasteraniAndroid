package com.anko.stinodes.ankoplication.web

import com.anko.stinodes.ankoplication.domain.Anime
import com.anko.stinodes.ankoplication.domain.Page
import com.anko.stinodes.ankoplication.domain.Release
import retrofit2.http.*
import rx.Observable

interface MAService {
    companion object {
        val LOGIN_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8"
    }

    @GET("api/releases")
    fun getReleases(): Observable<List<Release>>

    @GET("api/anime/filter")
    fun getAnime(
            @Query("page") page: Int,
            @Query("order") order: String,
            @Query("genres") genres: String
    ): Observable<Page<Anime>>

    @POST("auth/sign-in")
    fun login(
            @Header("Content-Type") contentType: String = LOGIN_CONTENT_TYPE,
            @Body credentials: String
    ): Observable<Unit>

}