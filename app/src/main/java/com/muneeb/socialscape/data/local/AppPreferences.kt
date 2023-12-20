package com.muneeb.socialscape.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = preferences.getBoolean("isLoggedIn", false)
        set(value) = preferences.edit().putBoolean("isLoggedIn", value).apply()

    var isEmailAccount: Boolean
        get() = preferences.getBoolean("isEmailAccount", false)
        set(value) = preferences.edit().putBoolean("isEmailAccount", value).apply()

    var email: String?
        get() = preferences.getString("email", null)
        set(value) = preferences.edit().putString("email", value).apply()

    var firstName: String?
        get() = preferences.getString("firstName", null)
        set(value) = preferences.edit().putString("firstName", value).apply()

    var lastName: String?
        get() = preferences.getString("lastName", null)
        set(value) = preferences.edit().putString("lastName", value).apply()

    var userName: String?
        get() = preferences.getString("userName", null)
        set(value) = preferences.edit().putString("userName", value).apply()

    var bio: String?
        get() = preferences.getString("bio", null)
        set(value) = preferences.edit().putString("bio", value).apply()

    var photo: String?
        get() = preferences.getString("photo", null)
        set(value) = preferences.edit().putString("photo", value).apply()

    var userAge: String?
        get() = preferences.getString("age", null)
        set(value) = preferences.edit().putString("age", value).apply()

    var genders: String?
        get() = preferences.getString("gender", null)
        set(value) = preferences.edit().putString("gender", value).apply()

    var userId: Int?
        get() = preferences.getInt("userId", -1)
        set(value) = preferences.edit().putInt("userId", value ?: -1).apply()

//    var follower: Int?
//        get() = preferences.getInt("follower", 0)
//        set(value) = preferences.edit().putInt("follower", value ?: -1).apply()
//
//    var following: Int?
//        get() = preferences.getInt("following", 0)
//        set(value) = preferences.edit().putInt("following", value ?: -1).apply()

    fun logout() {
        userId = null
        email = null
        firstName = null
        lastName = null
        userName = null
        bio = null
        photo = null
        isLoggedIn = false
        isEmailAccount = false
    }

}