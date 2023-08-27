package com.conectaedu.android.data.areas.remote

import com.conectaedu.android.data.model.AreaModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AreasRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAll() = firestore.collection("areas").snapshots().map { it.toObjects<AreaModel>() }
}