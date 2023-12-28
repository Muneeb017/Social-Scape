package com.muneeb.socialscape.model

data class Post(
    val userId: String? = null,
    val name: String? = null,
    val text: String? = null,
    val privacy: String? = null,
    val image: String? = null,
    val imageUser: String? = null,
    // Add other post properties as needed
)
//// When creating a new post
//val postsCollection = firestore.collection("posts")
//val newPost = Post(/*... populate post properties ...*/)
//
//val newPostRef = postsCollection.add(newPost)
//val postId = newPostRef.id // Obtain the auto-generated document ID as the postId