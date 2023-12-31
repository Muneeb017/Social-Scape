package com.muneeb.socialscape.model

import android.os.Parcelable
import com.muneeb.socialscape.utils.FirestoreUtil

@kotlinx.parcelize.Parcelize
data class User(
    val id: String? = FirestoreUtil.auth.currentUser?.uid,
    val name: String? = null,
    val userName: String? = null,
    val age: String? = null,
    val gender: String? = null,
    val image: String? = null,
    val email: String? = FirestoreUtil.auth.currentUser?.email,
    val bio: String? = null,
    val followers: List<String>? = null,
    val followings: List<String>? = null
) : Parcelable

@kotlinx.parcelize.Parcelize
data class OtherUser(
    val id: String? = null,
    val name: String? = null,
    val userName: String? = null,
    val age: String? = null,
    val gender: String? = null,
    val image: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val followers: List<String>? = null,
    val followings: List<String>? = null,
    var isFollowed: Boolean? = null,
    var isFollowing: Boolean? = null
) : Parcelable