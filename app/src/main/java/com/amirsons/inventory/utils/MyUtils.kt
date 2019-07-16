package com.amirsons.inventory.utils

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by taohid on 12/31/18
 * Email: taohid32@gmail.com
 */

object MyUtils {

    var FORMAT_DMYT = "EEEE MMMM dd, yyyy, hh:mm a"
    var FORMAT_DMY = "MMMM dd, yyyy"

    val currentDate: String
        get() {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return simpleDateFormat.format(Date())
        }

    val currentDateFormatted: String
        get() {
            val simpleDateFormat = SimpleDateFormat(FORMAT_DMY, Locale.getDefault())
            return simpleDateFormat.format(Date())
        }

    val currentDayOfWeek: String
        get() {
            val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            return simpleDateFormat.format(Date())
        }

    fun capitalizeFirstLatter(mainString: String?): String {
        return if (mainString == null || mainString.isEmpty()) "" else mainString.substring(0, 1).toUpperCase() + mainString.substring(1)
    }

    fun calculateNoOfColumns(context: Context, pixel: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / pixel).toInt()
    }

    fun generateUniqueId(): String {
        return UUID.randomUUID().toString()
    }

    fun objectPrettyPrint(`object`: Any): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(`object`)
    }

    fun valueOf(`object`: Any): String {
        return Gson().toJson(`object`)
    }

    fun <T> valueOf(`object`: String, className: Class<T>): T? {
        return Gson().fromJson<T>(`object`, className as Type)
    }

    fun getDateFromTime(hour: Int, minute: Int): Date {

        val date = Date()

        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, hour)// for 6 hour
        calendar.set(Calendar.MINUTE, minute)// for 0 min
        calendar.set(Calendar.SECOND, 0)// for 0 sec

        return calendar.time
    }

    fun getDateFromTimeString(time: String): Date {

        val timeSplit = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        return getDateFromTime(Integer.valueOf(timeSplit[0]), Integer.valueOf(timeSplit[1]))
    }

    fun getCurrentDate(pattern: String): String {
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(Date())
    }

    @Throws(ParseException::class)
    fun getDateFromDateString(date: String): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return simpleDateFormat.parse(date)
    }

    fun getFormattedDateFromMillis(time: Long): String {
        val callDayTime = Date(time)
        val parseFormat = SimpleDateFormat(FORMAT_DMY, Locale.getDefault())
        return parseFormat.format(callDayTime)
    }

    fun get12HourFrom24Hour(hourOfDay: Int, minute: Int): String {
        return (if (hourOfDay == 0) 12 else if (hourOfDay > 12) hourOfDay % 12 else hourOfDay).toString() + ":" + (if (minute < 10) "0$minute" else minute) + " " + if (hourOfDay >= 12) "PM" else "AM"
    }

    fun get12HourFrom24Hour(hourOfDay: String, minute: String): String {
        return get12HourFrom24Hour(Integer.valueOf(hourOfDay), Integer.valueOf(minute))
    }

    fun getHourMinuteFromString(time: String): Array<String> {
        return time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    fun isNumeric(s: String): Boolean {
        val formattedString = s.replace("[\\s]|[-]".toRegex(), "")
        return formattedString.matches("[-+]?\\d*\\.?\\d+".toRegex())
    }

    fun getRandomMaterialColor(context: Context): Int {

        var returnColor = Color.GRAY
        val arrayId = context.resources.getIdentifier("pie_color", "array", context.packageName)

        if (arrayId != 0) {
            val colors = context.resources.obtainTypedArray(arrayId)
            val index = (Math.random() * colors.length()).toInt()
            returnColor = colors.getColor(index, Color.GRAY)
            colors.recycle()
        }
        return returnColor
    }


    fun convertDpToPixel(dp: Float, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        return context.resources.displayMetrics
    }

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (getDisplayMetrics(context).densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}
