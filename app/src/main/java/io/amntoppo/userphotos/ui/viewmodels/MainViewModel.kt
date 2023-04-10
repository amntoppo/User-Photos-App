package io.amntoppo.userphotos.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.amntoppo.userphotos.data.api.UserApi
import io.amntoppo.userphotos.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: UserApi
    ): ViewModel() {
    private val userMutableData = MutableLiveData<List<User>>()
    val userData: LiveData<List<User>> = userMutableData

        fun loadUser(): LiveData<List<User>> {
            viewModelScope.launch {
                val userList = api.getUsers()
                userMutableData.value = userList
            }

            return userMutableData
        }
}