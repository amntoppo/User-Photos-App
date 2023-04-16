package io.amntoppo.userphotos.presentation.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.amntoppo.userphotos.data.remote.UserApi
import io.amntoppo.userphotos.data.repository.UserRepository
import io.amntoppo.userphotos.domain.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: UserApi,
    private val userRepository: UserRepository
    ): ViewModel() {
    private val userMutableData = MutableLiveData<List<User>>()
    val userData: LiveData<List<User>> = userMutableData
    val user = userRepository.getUsers().asLiveData()

        fun loadUser(): LiveData<List<User>> {
            viewModelScope.launch {
//                val userList = api.getUsers()
                val userList = userRepository.getUsers().asLiveData()
                userMutableData.value = userList.value?.data!!
            }

            return userMutableData
        }
}