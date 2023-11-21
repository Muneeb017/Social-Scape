package com.flashbid.luv.extensions

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.net.URL
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

fun String.checkIsEmpty(): Boolean =
    isNullOrEmpty() || "" == this || this.equals("null", ignoreCase = true)

fun getDate(date: Date): String {
    return try {
        val simple = SimpleDateFormat("dd MMM yyyy hh:MM", Locale.ENGLISH)
        return simple.format(date)
    } catch (e: Exception) {
        "0"
    }
}

fun EditText.textToString(): String = this.text.toString()

fun EditText.checkIsEmpty(): Boolean {
    return text == null || "" == textToString() || text.toString()
        .equals("null", ignoreCase = true)

}

fun EditText.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this.text).matches()
}

fun EditText.isValidPhoneNumber(): Boolean =
    text.matches("^((\\+92)?(0092)?(92)?(0)?)(3)([0-9]{9})\$".toRegex())

fun EditText.validPassword(): Boolean {
    return text.length > 5
}

fun View.showSoftKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun String.formatNumber(): String? {
    var num = this.replace(" ", "")
        .replace("-", "")
        .replace("(", "")
        .replace(")", "")
        .replace("+1", "")
    num = "+1$num"
    return if (num.length == 12) num else null
}

fun Fragment.copyText(text: String) {
    val clipboard: ClipboardManager? =
        requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip = ClipData.newPlainText("Account Number", text)
    clipboard?.setPrimaryClip(clip)
    Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
}

@SuppressLint("NewApi")
fun String.convertDate(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    val inputDate = LocalDateTime.parse(this, inputFormatter)
    return inputDate.format(outputFormatter)

}

//fun String.getTimeInAgo(): String {
//    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
//    val inputDate = inputFormat.parse(this)
//
//    val currentTimestamp = System.currentTimeMillis()
//    val elapsedTime = currentTimestamp - inputDate!!.time
//    val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime)
//    val hours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
//    val days = TimeUnit.MILLISECONDS.toDays(elapsedTime)
//    val weeks = days / 7
//    val months = days / 30
//    val years = days / 365
//
//    val output = when {
//        minutes == 0L -> MainActivity.instance.getString(R.string.just_now)
//        minutes < 60 -> MainActivity.instance.getString(R.string.minutes_ago, minutes.toString())
//        hours == 1L -> MainActivity.instance.getString(R.string.hour_ago, "1")
//        hours < 24 -> MainActivity.instance.getString(R.string.hours_ago, hours.toString())
//        days == 1L -> MainActivity.instance.getString(R.string.day_ago, "1")
//        days < 7 -> MainActivity.instance.getString(R.string.days_ago, days.toString())
//        weeks == 1L -> MainActivity.instance.getString(R.string.week_ago, "1")
//        weeks < 4 -> MainActivity.instance.getString(R.string.weeks_ago, weeks.toString())
//        months == 1L -> MainActivity.instance.getString(R.string.month_ago, "1")
//        months < 12 -> MainActivity.instance.getString(R.string.months_ago, months.toString())
//        years == 1L -> MainActivity.instance.getString(R.string.year_ago, "1")
//        else -> MainActivity.instance.getString(R.string.years_ago, years.toString())
//    }
//    return output
//}

fun Double.currentFormat(): String {
    return "$" + String.format("%.2f", this)
}

fun Double.formatDecimal(places: Int = 2): String {
    return String.format("%.${places}f", this)
}

fun Boolean.convertToInt(): Int {
    return if (this) 1 else 0
}

fun Int.shortenAmount(): String {
    val input = this
    val output = when {
        input >= 1000000000 -> "${(input.toDouble() / 1000000000).formatDecimal()}b"
        input >= 1000000 -> "${(input.toDouble() / 1000000).formatDecimal()}m"
        input >= 100000 -> "${(input.toDouble() / 1000).formatDecimal()}k"
        input >= 1000 -> {
            val formatter = DecimalFormat("#,###")
            formatter.decimalFormatSymbols.groupingSeparator = ' '
            formatter.format(input)
        }
        else -> "$input"
    }
    return output.replace(","," ").replace("."," ")
}

fun Fragment.getCopiedText(): String {
    val manager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    return manager.primaryClip?.getItemAt(0)?.text.toString()
}

fun String.appendHttpIfMissing(): String {
    if (this.startsWith("http://") || this.startsWith("https://")) {
        return this
    }
    return "https://$this"
}

fun String.isValidUrl(): Boolean {
    return try {
        val url = URL(this.appendHttpIfMissing())
        url.toURI()
        true
    } catch (e: Exception) {
        false
    }
}