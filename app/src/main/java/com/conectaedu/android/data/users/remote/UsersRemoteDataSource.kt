package com.conectaedu.android.data.users.remote

import com.conectaedu.android.data.model.Result
import com.conectaedu.android.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    suspend fun setUser(userModel: UserModel): Result<Nothing> {
        return try {
            firestore.document("users/${userModel.id}").set(userModel).await()
            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun getUser(id: String) =
        firestore.document("users/$id").snapshots().map { it.toObject<UserModel>()!! }
}