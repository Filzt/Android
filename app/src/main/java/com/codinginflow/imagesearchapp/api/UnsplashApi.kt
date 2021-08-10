package com.codinginflow.imagesearchapp.api

import android.graphics.pdf.PdfDocument
import com.codinginflow.imagesearchapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    companion object{
        const val BASE_URL = "http://api.unsplash.com/"
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
    }

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") client_id: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_pag") perPage: Int
    ): UnsplashResponse
}