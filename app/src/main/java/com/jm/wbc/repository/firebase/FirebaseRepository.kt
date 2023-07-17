package com.jm.wbc.repository.firebase

import android.net.Uri
import com.google.firebase.firestore.QuerySnapshot

interface FirebaseRepository {

    suspend fun signIn(email: String, passwd: String): Boolean

    suspend fun createAccount(email: String, passwd: String): Boolean

    suspend fun uploadProfileImage(uri: Uri): Boolean

    suspend fun getProfileImage(): Uri?

    suspend fun insertMyBookmark(stationID: String, routeID: String, routeNm: String): Boolean

    suspend fun getMyBookmark(): QuerySnapshot

    suspend fun deleteBookmark(busNm: String): Boolean

}