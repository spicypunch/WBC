package com.jm.wbc.ui.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jm.wbc.repository.firebase.FirebaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl
    ) : ViewModel() {

    private var _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

        fun signIn(email: String, passwd: String) {
            viewModelScope.launch {
                _success.value = firebaseRepository.signIn(email, passwd)
            }
        }

}