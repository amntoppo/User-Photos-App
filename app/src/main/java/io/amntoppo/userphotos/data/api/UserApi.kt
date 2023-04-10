package io.amntoppo.userphotos.data.api

import io.amntoppo.userphotos.data.model.Photo
import io.amntoppo.userphotos.data.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("photos")
    suspend fun getPhotos(id: Int): List<Photo>
}