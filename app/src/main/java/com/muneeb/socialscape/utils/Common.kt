package com.muneeb.socialscape.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.muneeb.socialscape.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.*

@SuppressLint("NewApi")
fun isNetworkAvailable(context: Context): Boolean {
    val isConnected: Boolean
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val activeNetwork =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    isConnected = when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }

    return isConnected
}

@SuppressLint("NewApi")
fun String.parseServerDate(pattern: String): String {
    val default = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val dateTime: LocalDateTime = LocalDateTime.parse(this, default)
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return dateTime.format(formatter)
}

fun View.getColor(colorInt: Int): Int {
    return ContextCompat.getColor(this.context, colorInt)
}
//
//fun View.padding(edges: Set<Edge>, value: Int) {
//    var top = paddingTop
//    var left = paddingLeft
//    var bottom = paddingBottom
//    var right = paddingRight
//    for (edge in edges) {
//        when (edge) {
//            Edge.TOP -> top = value
//            Edge.LEFT -> left = value
//            Edge.BOTTOM -> bottom = value
//            Edge.RIGHT -> right = value
//        }
//    }
//    setPadding(left, top, right, bottom)
//}

fun AutoCompleteTextView.setArrayAdapter(list: ArrayList<String>) {
    val adapter =
        ArrayAdapter(this.context, R.layout.item_dropdown, list)
    this.setAdapter(adapter)
}

fun ImageView.loadImageFromUrl(
    aImageUrl: String? = "",
    userImage: Boolean = true
) {
    val placeholder = if (userImage) R.drawable.ic_person else R.drawable.ic_person
    if (!aImageUrl.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(Uri.parse(aImageUrl))
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(placeholder)
            .into(this)
    } else displayBlankImage(this.context, placeholder)
}

fun ImageView.displayBlankImage(aContext: Context, aPlaceHolderImage: Int) {
    Glide.with(aContext)
        .load(aPlaceHolderImage)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

//fun Fragment.openImagePicker() {
//    val intent = Intent(Intent.ACTION_PICK)
//    intent.type = "image/*"
//    startActivityForResult(intent, RequestCodes.GALLERY_IMAGE)
//}
//
//fun Fragment.shareImage(bitmap: Bitmap) {
//    val shareIntent = Intent()
//    shareIntent.action = Intent.ACTION_SEND
//    shareIntent.putExtra(Intent.EXTRA_STREAM, context?.getImageUri(bitmap))
//    shareIntent.type = "image/jpeg"
//    startActivity(Intent.createChooser(shareIntent, "Share QR code"))
//}