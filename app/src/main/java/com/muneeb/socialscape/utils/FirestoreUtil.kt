package com.muneeb.socialscape.utils

import android.os.Parcelable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.model.User

object FirestoreUtil {

    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
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
        getCurrentUserDocumentRef()?.get()?.addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val user = documentSnapshot.toObject(User::class.java)
                user?.let { onSuccess(it) }
            } else {
                onFailure(Exception("User document does not exist"))
            }
        }?.addOnFailureListener { e ->
            onFailure(e)
        }
    }

    // Function to create or update user data
    fun getAllUsers(onSuccess: (List<User>) -> Unit, onFailure: (Exception) -> Unit) {
        val usersCollection = firestore.collection("users")

        usersCollection.get().addOnSuccessListener { querySnapshot ->
            val usersList = mutableListOf<User>()

            for (document in querySnapshot.documents) {
                val user = document.toObject(User::class.java)
                user?.let { usersList.add(it) }
            }

            onSuccess(usersList)
        }.addOnFailureListener { e ->
            onFailure(e)
        }
    }

    fun createOrUpdateUserData(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        getCurrentUserDocumentRef()?.set(user)?.addOnSuccessListener {
            onSuccess()
        }?.addOnFailureListener { e ->
            onFailure(e)
        }
    }

    fun updateProfileImage(imageUrl: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val usersCollection = firestore.collection("users")

            val userDocumentRef = usersCollection.document(userId)

            userDocumentRef.update("image", imageUrl)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    // Function to create a post
    fun createPost(
        text: String,
        privacy: String,
        name: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val postsCollection = firestore.collection("posts")
            val newPost = Post(userId = userId, text = text, privacy = privacy)
            postsCollection.add(newPost).addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener { e ->
                onFailure(e)
            }
        } else {
            onFailure(Exception("User not authenticated"))
        }

    }

    // Function to get all uploaded posts
    fun getUploadedPosts(onSuccess: (List<Post>) -> Unit, onFailure: (Exception) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val postsCollection = firestore.collection("posts")

            postsCollection.whereEqualTo("userId", userId) // Filter posts by current user ID
                .get().addOnSuccessListener { querySnapshot ->
                    val postsList = mutableListOf<Post>()

                    for (document in querySnapshot.documents) {
                        val post = document.toObject(Post::class.java)
                        post?.let { postsList.add(it) }
                    }

                    onSuccess(postsList)
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

}