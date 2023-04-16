package io.amntoppo.userphotos.data.remote

import io.amntoppo.userphotos.domain.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("photos")
    suspend fun getPhoto(@Query("id")photoId: Int): List<Photo>
}