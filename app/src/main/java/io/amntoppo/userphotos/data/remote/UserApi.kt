package io.amntoppo.userphotos.data.remote

import io.amntoppo.userphotos.domain.model.Photo
import io.amntoppo.userphotos.domain.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("photos")
    suspend fun getPhotos(id: Int): List<Photo>
}