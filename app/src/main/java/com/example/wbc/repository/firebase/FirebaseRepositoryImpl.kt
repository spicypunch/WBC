package com.example.wbc.repository.firebase

import android.net.Uri
import android.util.Log
import com.example.wbc.data.entity.BookmarkEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val imageStorage: FirebaseStorage
) : FirebaseRepository {

    private val currentTime: Long = System.currentTimeMillis()
    private val dateFormat = SimpleDateFormat("yy-MM-dd_HH:mm:ss")
    override suspend fun signIn(email: String, passwd: String): Boolean {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, passwd).await()
            (authResult.user != null)
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun createAccount(email: String, passwd: String): Boolean {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, passwd).await()
            (authResult.user != null)
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun uploadProfileImage(uri: Uri): Boolean {
        return try {
            val imageRef = imageStorage.reference.child("profile/")
                .child("image_${auth.currentUser!!.uid}.jpg")
            imageRef.putFile(uri).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getProfileImage(): Uri? {
        return try {
            val imgRef =
                imageStorage.reference.child("profile/image_${auth.currentUser!!.uid}.jpg")
            imgRef.downloadUrl.await()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertMyBookmark(
        stationID: String,
        routeID: String,
        routeNm: String
    ): Boolean {
        return try {
            val bookmarkEntity = BookmarkEntity(stationID, routeID, routeNm)
            auth.currentUser?.let {
                db.collection(it.uid)
                    .document(dateFormat.format(currentTime))
                    .set(bookmarkEntity).await()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getMyBookmark(): QuerySnapshot {
            val dbResult = auth.currentUser?.let {
                db.collection(it.uid)
                    .get()
                    .await()
            }
        return dbResult!!
    }
}