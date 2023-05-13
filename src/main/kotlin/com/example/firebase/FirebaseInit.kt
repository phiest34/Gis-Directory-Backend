package com.example.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.FirebaseOptions.builder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*

import java.io.FileInputStream


fun initFirebase() {
    val serviceAccount = FileInputStream("src/main/resources/gis-directory-firebase-adminsdk-ymkuw-b6c849360a.json")
    val options: FirebaseOptions = builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://gis-directory-default-rtdb.firebaseio.com")
        .build()
    FirebaseApp.initializeApp(options)
}

object WebmapsFireBaseStorage {

    private val dbInstance: DatabaseReference
        get() = FirebaseDatabase.getInstance().reference

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun putWebMapAsync(createdWebMap: FirebaseModel): Boolean = suspendCancellableCoroutine {
        dbInstance.child("webmaps").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                if (snapshot == null) {
                    it.resume(false, Throwable::printStackTrace)
                }
                val notNull = (snapshot?.value as List<*>).filterNotNull()
                dbInstance.child("webmaps").child(notNull.size.toString())
                    .setValue(createdWebMap.toMap()) { error, ref ->
                        it.resume(error == null, Throwable::printStackTrace)
                    }
            }

            override fun onCancelled(error: DatabaseError?) {
                it.resume(false, Throwable::printStackTrace)
            }

        })
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun loadWebmapsAsync(): List<FirebaseModel> = suspendCancellableCoroutine {
        val list = mutableListOf<FirebaseModel?>()
        dbInstance.child("webmaps").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot?) {
                val value = snapshot?.value
                val notNull = (value as List<*>).filterNotNull()
                notNull.forEach { hashMap ->
                    val map = hashMap as Map<String, String>
                    list.add(FirebaseModel.fromMap(map))
                }
                it.resume(list.toList().filterNotNull()) { thr ->
                    println(thr.stackTraceToString())
                }
            }

            override fun onCancelled(error: DatabaseError?) {
                it.resume(emptyList()) { thr ->
                    println(thr.stackTraceToString())
                }
            }
        })
    }
}
