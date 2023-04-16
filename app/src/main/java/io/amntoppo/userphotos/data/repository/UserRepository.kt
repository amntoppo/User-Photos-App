package io.amntoppo.userphotos.data.repository

import androidx.room.withTransaction
import io.amntoppo.userphotos.data.local.UserDao
import io.amntoppo.userphotos.data.local.UserDatabase
import io.amntoppo.userphotos.data.remote.PhotoApi
import io.amntoppo.userphotos.data.remote.UserApi
import io.amntoppo.userphotos.domain.model.Photo
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.domain.repository.IUserRepository
import io.amntoppo.userphotos.utils.NetworkBoundResource
import io.amntoppo.userphotos.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(

    private val userDB: UserDatabase,
    private val userApi: UserApi,
    private val photoApi: PhotoApi
) : IUserRepository{
    private val userDao = userDB.userDao()
    private val photoDao = userDB.photoDao()
    override fun getUsers() = NetworkBoundResource(
        query = {
            userDao.getAllUsers()
        },
        fetch = {
            userApi.getUsers()
        },
        saveFetchRequest = { users ->
            userDB.withTransaction {
                userDao.deleteAllUsers()
                userDao.insertUsers(users)
            }
        },
        shouldFetch = { true }
    )

    override fun getPhotos(id: Int) = NetworkBoundResource(
        query = {
            photoDao.getPhoto(id)
        },
        fetch = {
            photoApi.getPhoto(id)
        },
        saveFetchRequest = { photo ->
            userDB.withTransaction {
                photoDao.deletePhoto(id)
                photoDao.insertPhoto(photo.first())
            }
        },
        shouldFetch = { true }
    )
}