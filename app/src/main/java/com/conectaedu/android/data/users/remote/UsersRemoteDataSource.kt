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
    suspend fun save(userModel: UserModel): Result<Nothing> {
        return try {
            firestore.document("users/${userModel.id}").set(userModel).await()
            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun get(id: String) =
        firestore.document("users/$id").snapshots().map { it.toObject<UserModel>()!! }

    suspend fun registerArea(userModel: UserModel, areaId: String): Result<Nothing> {
        return try {
            val areas = userModel.registeredAreaIds.toMutableList()
            areas.add(areaId)
            firestore.document("users/${userModel.id}").update("registeredAreaIds", areas.toList())
                .await()
            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}