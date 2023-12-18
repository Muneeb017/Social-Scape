package com.muneeb.socialscape.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtil {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    // Get the current authenticated user ID
    private val currentUserId: String?
        get() = auth.currentUser?.uid

    // Reference to the 'users' collection
    private val usersCollection: CollectionReference
        get() = firestore.collection("users")

    // Function to get the current user's document reference
    private fun getCurrentUserDocumentRef(): DocumentReference? {
        return currentUserId?.let { usersCollection.document(it) }
    }

    // Function to get user data
    fun getUserData(onSuccess: (User) -> Unit, onFailure: (Exception) -> Unit) {
        getCurrentUserDocumentRef()?.get()
            ?.addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val user = documentSnapshot.toObject(User::class.java)
                    user?.let { onSuccess(it) }
                } else {
                    onFailure(Exception("User document does not exist"))
                }
            }
            ?.addOnFailureListener { e ->
                onFailure(e)
            }
    }

    // Function to create or update user data
    fun createOrUpdateUserData(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        getCurrentUserDocumentRef()?.set(user)
            ?.addOnSuccessListener {
                onSuccess()
            }
            ?.addOnFailureListener { e ->
                onFailure(e)
            }
    }

    data class User(
        val id: String? = auth.currentUser?.uid,
        val name: String? = null,
        val userName: String? = null,
        val age: String? = null,
        val gender: String? = null,
        val email: String? = auth.currentUser?.email,
        val bio: String? = null,
        // Add other user properties as needed
    )
}
