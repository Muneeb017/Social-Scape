package com.muneeb.socialscape.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.muneeb.socialscape.model.OtherUser
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
    fun getAllUsers(onSuccess: (List<OtherUser>) -> Unit, onFailure: (Exception) -> Unit) {
        val usersCollection = firestore.collection("users")
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid

        usersCollection.get().addOnSuccessListener { querySnapshot ->
            val usersList = mutableListOf<OtherUser>()

            for (document in querySnapshot.documents) {
                val user = document.toObject(OtherUser::class.java)
                user?.let {
                    // Calculate follow status
                    val isFollowed = it.followers?.contains(currentUserId) ?: false
                    val isFollowing = it.followings?.contains(currentUserId) ?: false

                    // Create a new User object with follow status
                    val userWithStatus = it.copy(isFollowed = isFollowed, isFollowing = isFollowing)

                    usersList.add(userWithStatus)
                }
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

    fun updateProfileImage(
        imageUrl: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val usersCollection = firestore.collection("users")

            val userDocumentRef = usersCollection.document(userId)

            userDocumentRef.update("image", imageUrl).addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener { e ->
                onFailure(e)
            }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    // Function to create a post
    fun createPost(
        name: String,
        text: String,
        privacy: String,
        image: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val postsCollection = firestore.collection("posts")
            val newPost =
                Post(userId = userId, text = text, privacy = privacy, name = name, image = image)
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

    fun followUser(userIdToFollow: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid

        if (currentUserId != null) {
            val usersCollection = firestore.collection("users")

            // Update the current user's followers list to include the user to follow
            usersCollection.document(currentUserId)
                .update("followings", FieldValue.arrayUnion(userIdToFollow)).addOnSuccessListener {
                    // Update the followed user's data to mark the current user as a follower
                    usersCollection.document(userIdToFollow)
                        .update("followers", FieldValue.arrayUnion(currentUserId))
                        .addOnSuccessListener {
                            onSuccess()
                        }.addOnFailureListener { e ->
                            onFailure(e)
                        }
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    fun getPostsFromFollowedUsers(onSuccess: (List<Post>) -> Unit, onFailure: (Exception) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid

        if (currentUserId != null) {
            val usersCollection = firestore.collection("users")
            val postsCollection = firestore.collection("posts")

            usersCollection.document(currentUserId).get().addOnSuccessListener { documentSnapshot ->
                    val currentUserData = documentSnapshot.toObject(User::class.java)
                    val followers = currentUserData?.followings ?: emptyList()

                    if (followers.isNotEmpty()) {
                        postsCollection.whereIn("userId", followers).get()
                            .addOnSuccessListener { querySnapshot ->
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
                        onSuccess(emptyList())
                    }
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    fun unfollowUser(
        userIdToUnfollow: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit
    ) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid

        if (currentUserId != null) {
            val usersCollection = firestore.collection("users")

            // Remove the user to unfollow from the current user's followers list
            usersCollection.document(currentUserId)
                .update("followings", FieldValue.arrayRemove(userIdToUnfollow))
                .addOnSuccessListener {
                    // Remove the current user from the followers list of the user to unfollow
                    usersCollection.document(userIdToUnfollow)
                        .update("followers", FieldValue.arrayRemove(currentUserId))
                        .addOnSuccessListener {
                            onSuccess()
                        }.addOnFailureListener { e ->
                            onFailure(e)
                        }
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    fun searchUsers(
        query: String, onSuccess: (List<OtherUser>) -> Unit, onFailure: (Exception) -> Unit
    ) {
        val usersCollection = firestore.collection("users")
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            usersCollection.whereGreaterThanOrEqualTo("name", query).whereLessThanOrEqualTo(
                    "name", query + "\uf8ff"
                ) // \uf8ff is a high surrogate code point
                .get().addOnSuccessListener { nameQuerySnapshot ->
                    val matchingUsersList = mutableListOf<OtherUser>()

                    for (document in nameQuerySnapshot.documents) {
                        val user = document.toObject(OtherUser::class.java)
                        user?.let {
                            // Exclude the current user from the search results
                            if (it.id != currentUser.uid) {
                                matchingUsersList.add(it)
                            }
                        }
                    }

                    // Perform a similar query for username
                    usersCollection.whereGreaterThanOrEqualTo("userName", query)
                        .whereLessThanOrEqualTo("userName", query + "\uf8ff").get()
                        .addOnSuccessListener { usernameQuerySnapshot ->
                            for (document in usernameQuerySnapshot.documents) {
                                val user = document.toObject(OtherUser::class.java)
                                user?.let {
                                    // Exclude the current user and avoid duplicates from the previous query
                                    if (it.id != currentUser.uid && !matchingUsersList.contains(it)) {
                                        matchingUsersList.add(it)
                                    }
                                }
                            }

                            // Check follow status for each user
                            matchingUsersList.forEach { user ->
                                user.isFollowed = user.followers?.contains(currentUser.uid) ?: false
                                user.isFollowing =
                                    user.followings?.contains(currentUser.uid) ?: false
                            }

                            onSuccess(matchingUsersList)
                        }.addOnFailureListener { e ->
                            onFailure(e)
                        }
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    fun likePost(postId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid

        if (currentUserId != null) {
            val postsCollection = firestore.collection("posts")

            // Update the post's likes list to include the current user
            postsCollection.document(postId).update("likes", FieldValue.arrayUnion(currentUserId))
                .addOnSuccessListener {
                    onSuccess()
                }.addOnFailureListener { e ->
                    onFailure(e)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

}