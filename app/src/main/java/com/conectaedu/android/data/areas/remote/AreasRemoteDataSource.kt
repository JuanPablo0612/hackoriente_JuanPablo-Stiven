package com.conectaedu.android.data.areas.remote

import com.conectaedu.android.data.model.AreaModel
import com.conectaedu.android.data.model.Result
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AreasRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAll() = firestore.collection("areas").snapshots().map { it.toObjects<AreaModel>() }

    suspend fun add(areaModel: AreaModel): Result<Nothing> {
        return try {
            val areas = firestore.collection("areas")
            val id = areas.document().id
            val model = areaModel.copy(id = id)
            areas.document(id).set(model).await()

            Result.Success(null)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}