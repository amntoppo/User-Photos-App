package io.amntoppo.userphotos.presentation.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.amntoppo.userphotos.data.remote.UserApi
import io.amntoppo.userphotos.data.repository.UserRepository
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: UserApi,
    private val userRepository: UserRepository
    ): ViewModel() {
    private val userMutableData = MutableLiveData<Resource<out List<User>>>()
    private val userData: LiveData<Resource<out List<User>>> = userMutableData

    private val mutableSelectedItem = MutableLiveData<User>()
    val selectedItem: LiveData<User> get() = mutableSelectedItem
//    val user = userRepository.getUsers().asLiveData()

        fun loadUser(): LiveData<Resource<out List<User>>> {
            viewModelScope.launch {
                userRepository.getUsers().collect {
                    userMutableData.value = it
                }
            }
            return userData
        }

    fun selectedUser(user: User) {
        mutableSelectedItem.value = user
    }
}