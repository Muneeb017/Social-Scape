package com.muneeb.socialscape.model

data class Post(
    val userId: String? = null,
    val name: String? = null,
    val text: String? = null,
    val privacy: String? = null,
    val image: String? = null,
    // Add other post properties as needed
)