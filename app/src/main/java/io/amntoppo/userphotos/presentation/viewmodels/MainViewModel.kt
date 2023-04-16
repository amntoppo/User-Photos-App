package io.amntoppo.userphotos.presentation.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.amntoppo.userphotos.data.remote.PhotoApi
import io.amntoppo.userphotos.data.remote.UserApi
import io.amntoppo.userphotos.data.repository.UserRepository
import io.amntoppo.userphotos.domain.model.Photo
import io.amntoppo.userphotos.domain.model.User
import io.amntoppo.userphotos.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
    ): ViewModel() {
    private val userMutableData = MutableLiveData<Resource<out List<User>>>()
    private val userData: LiveData<Resource<out List<User>>> = userMutableData

    private val photoMutableData = MutableLiveData<Resource<out Photo>>()
    private val photoData: LiveData<Resource<out Photo>> = photoMutableData

    private val mutableSelectedItem = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = mutableSelectedItem

        fun loadUser(): LiveData<Resource<out List<User>>> {
            viewModelScope.launch {
                userRepository.getUsers().collect {
                    userMutableData.value = it
                }
            }
            return userData
        }

    fun loadPhoto(id: Int): LiveData<Resource<out Photo>> {
        viewModelScope.launch {
            userRepository.getPhotos(id).collect {
                photoMutableData.value = it
            }
        }
        return photoData
    }

    fun selectedUser(user: User) {
        mutableSelectedItem.value = user
    }
}