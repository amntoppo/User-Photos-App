package io.amntoppo.userphotos.domain.repository

import androidx.lifecycle.LiveData
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUsers(): Flow<Resource<out List<User>>>
}