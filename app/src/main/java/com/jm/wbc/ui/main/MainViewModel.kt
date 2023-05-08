package com.jm.wbc.ui.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jm.wbc.repository.firebase.FirebaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl
) : ViewModel() {

    private var _uri = MutableLiveData<Uri>()
    val uri: LiveData<Uri>
        get() = _uri

    fun getProfileImage() {
        viewModelScope.launch {
            _uri.value = firebaseRepository.getProfileImage()
        }
    }

}