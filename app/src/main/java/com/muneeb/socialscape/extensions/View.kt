package com.muneeb.socialscape.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.text.InputFilter
import android.view.View
import android.view.animation.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.io.OutputStream
import java.util.*

fun View.slideDown() {
    val animate = TranslateAnimation(
        0F,  // fromXDelta
        0F,  // toXDelta
        0F,  // fromYDelta
        this.height.toFloat()
    ) // toYDelta
    animate.duration = 500
    this.startAnimation(animate)
}

fun View.slideUp() {
    val animate = ScaleAnimation(
        1F,  // fromXDelta
        1F,  // toXDelta
        1F,  // fromYDelta
        0F
    ) // toYDelta
    animate.duration = 500
    this.startAnimation(animate)
}

fun View.fadeIn(duration: Int = 400) {
    clearAnimation()
    val alphaAnimation = AlphaAnimation(this.alpha, 1.0f)
    alphaAnimation.duration = duration.toLong()
    startAnimation(alphaAnimation)
}

fun View.fadeOut(duration: Int = 400) {
    clearAnimation()
    val alphaAnimation = AlphaAnimation(this.alpha, 0f)
    alphaAnimation.duration = duration.toLong()
    startAnimation(alphaAnimation)
}

fun View.changeTint(color: Int) {
    this.backgroundTintList =
        ContextCompat.getColorStateList(
            context, color
        )
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

//@SuppressLint("NewApi")
//fun Activity.snackBar(
//    msg: String,
//    length: Int = Snackbar.LENGTH_SHORT
//) =
//    Snackbar.make(findViewById(android.R.id.content), msg, length)
//        .setBackgroundTint(getColor(R.color.redgradstart))
//        .apply {
//            animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
//        }
//        .show()

//fun Fragment.snackBar(msg: String) = activity!!.snackBar(msg)

fun RecyclerView.setGridLayout(span: Int = 2) {
    layoutManager = GridLayoutManager(context, span)
}

fun RecyclerView.setVerticalLayout(aReverseLayout: Boolean = false) {
    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, aReverseLayout)
}

fun RecyclerView.setHorizontalLayout(aReverseLayout: Boolean = false) {
    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, aReverseLayout)
}

fun Activity.hideSoftKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

enum class TextState {
    ERROR,
    SUCCESS,
    NEUTRAL
}

//fun TextView.setTextState(info: String, state: TextState = TextState.NEUTRAL) {
//    this.apply {
//        show()
//        text = info
//        when (state) {
//            TextState.ERROR -> {
//                setTextColor(getColor(R.color.redgradstart))
//                setBackgroundColor(getColor(R.color.semired))
//            }
//
//            TextState.SUCCESS -> {
//                setTextColor(getColor(R.color.green))
//                setBackgroundColor(getColor(R.color.semigreen))
//            }
//
//            TextState.NEUTRAL -> {
//                setTextColor(getColor(R.color.gray))
//                setBackgroundColor(getColor(R.color.md_theme_light_background))
//            }
//        }
//    }
//}

//fun MaterialCardView.setFollowState() {
//    this.apply {
//        setCardBackgroundColor(getColor(R.color.semiblue))
//        strokeWidth = 0
//    }
//}
//
//fun MaterialCardView.setUnfollowState() {
//    this.apply {
//        setCardBackgroundColor(getColor(R.color.md_theme_light_background))
//        strokeWidth = 1
//    }
//}
//
//fun View.scaleEffect() {
//    val animation: Animation =
//        AnimationUtils.loadAnimation(this.context, R.anim.scale_anim)
//    this.startAnimation(animation)
//}

fun EditText.disableSpaces() {
    val filter =
        InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (Character.isWhitespace(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }

    this.filters = arrayOf(filter)
}

fun Context.getImageUri(bitmap: Bitmap): Uri? {
    val values = ContentValues()
    values.put(MediaStore.Images.Media.TITLE, "Qr Code")
    values.put(MediaStore.Images.Media.DESCRIPTION, "LUV QR code sharing")
    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    val uri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    val outputStream: OutputStream? = uri?.let { contentResolver.openOutputStream(it) }
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    outputStream?.close()
    return uri
}

fun View.createBitmapFromView(): Bitmap {
    val view = this
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

//fun generateRoundedQRCode(context: Context, text: String, size: Int, cornerRadius: Float): Bitmap {
//    val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
//    hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
//    val writer = QRCodeWriter()
//    val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size, hints)
//    val pixels = IntArray(size * size)
//    for (y in 0 until size) {
//        val offset = y * size
//        for (x in 0 until size) {
//            pixels[offset + x] = if (bitMatrix[x, y]) 0xFF000000.toInt() else -0x1
//        }
//    }
//    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
//    bitmap.setPixels(pixels, 0, size, 0, 0, size, size)
//    val drawable = BitmapDrawable(context.resources, bitmap)
//    val roundedDrawable = RoundedBitmapDrawableFactory.create(context.resources, drawable.bitmap)
//    roundedDrawable.cornerRadius = cornerRadius
//    val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
//    val canvas = Canvas(output)
//    roundedDrawable.setBounds(0, 0, size, size)
//    roundedDrawable.draw(canvas)
//    return output
//}

fun View.setOnClickWithDebounce(action: (View) -> Unit) {
    setOnClickListener {
        isEnabled = false
        action.invoke(this)
        postDelayed({ isEnabled = true }, 1000)
    }
}


