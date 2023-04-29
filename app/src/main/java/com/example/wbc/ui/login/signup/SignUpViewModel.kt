package com.example.wbc.ui.login.signup

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbc.repository.firebase.FirebaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl
) : ViewModel() {

    private var _imageUploadSuccess = MutableLiveData<Boolean>()
    val imageUploadSuccess: LiveData<Boolean>
        get() = _imageUploadSuccess

    private var _createSuccess = MutableLiveData<Boolean>()
    val createSuccess: LiveData<Boolean>
        get() = _createSuccess

    fun createAccount(email: String, passwd: String) {
        viewModelScope.launch {
            _createSuccess.value = firebaseRepository.createAccount(email, passwd)
        }
    }

    fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            _imageUploadSuccess.value = firebaseRepository.uploadProfileImage(uri)
        }
    }
}