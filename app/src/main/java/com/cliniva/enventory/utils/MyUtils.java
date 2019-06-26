package com.cliniva.enventory.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by taohid on 12/31/18
 * Email: taohid32@gmail.com
 */

public class MyUtils {

    public static String FORMAT_DMYT = "EEEE MMMM dd, yyyy, hh:mm a";
    public static String FORMAT_DMY = "dd MMMM, yyyy (EEEE)";

    public static class NumberUtils {

        public static int feetToInch(int feet){
            return feet * 12;
        }

        public static int feetToInch(int feet, int inch){
            return feetToInch(feet) + inch;
        }

        public static double inchToMeter(int inch){
            return inch / 39.370;
        }

        public static double calculateBmi(int weight, double meter){
            return weight / (meter*meter);
        }
    }

    public static String capitalizeFirstLatter(String mainString){

        if (mainString == null || mainString.isEmpty())
            return "";

        return mainString.substring(0,1).toUpperCase() + mainString.substring(1);
    }

    public static int calculateNoOfColumns(Context context, int pixel) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / pixel);
    }

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String objectPrettyPrint(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    public static String valueOf(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T valueOf(String object, Class<T> className) {
        return new Gson().fromJson(object, (Type) className);
    }

    public static String getCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static Date getDateFromTime(int hour, int minute){

        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);// for 6 hour
        calendar.set(Calendar.MINUTE, minute);// for 0 min
        calendar.set(Calendar.SECOND, 0);// for 0 sec

        return calendar.getTime();
    }

    public static Date getDateFromTimeString(String time){

        String[] timeSplit = time.split(":");

        return getDateFromTime(Integer.valueOf(timeSplit[0]), Integer.valueOf(timeSplit[1]));
    }

    public static boolean isTimeOverlapping(Date start1, Date end1, Date start2, Date end2) {

        // first check is end time is before start time,
        // if its happen then time overlapping occur
        if (end1.before(start1)){
            return true;
        }

        // now check is time interval is not minimum 30 or not
        long mills = end1.getTime() - start1.getTime();
        int hours = (int) mills / (1000 * 60 * 60);
        int mins = (int) (mills / (1000*60)) % 60;

        if (hours == 0) {
            if (mins < 30) {
                return true;
            }
        }

        // now check the time overlapping
        if (start1.before(end2)){
            return !end1.before(start2);
        } else {
            return false;
        }
    }

    public static String getCurrentDate(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentDayOfWeek(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static Date getDateFromDateString(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return simpleDateFormat.parse(date);
    }

    public static String getFormattedDateFromMillis(long time) {
        Date callDayTime = new Date(time);
        SimpleDateFormat parseFormat = new SimpleDateFormat(FORMAT_DMY, Locale.getDefault());
        return parseFormat.format(callDayTime);
    }

    public static String get12HourFrom24Hour(int hourOfDay, int minute){
        return (hourOfDay == 0 ? 12 : (hourOfDay > 12) ? hourOfDay % 12 : hourOfDay) + ":" + (minute < 10 ? ("0" + minute) : minute) + " " + ((hourOfDay >= 12) ? "PM" : "AM");
    }

    public static String get12HourFrom24Hour(String hourOfDay, String minute){
        return get12HourFrom24Hour(Integer.valueOf(hourOfDay), Integer.valueOf(minute));
    }

    public static String[] getHourMinuteFromString(String time){
        return time.split(":");
    }

    public static boolean isNumeric(String s) {
        String formattedString = s.replaceAll("[\\s]|[-]", "");
        return formattedString.matches("[-+]?\\d*\\.?\\d+");
    }

    public int getRandomMaterialColor(Context context) {

        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("pie_color", "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }


    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static DisplayMetrics getDisplayMetrics(Context context){
        return context.getResources().getDisplayMetrics();
    }

    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) getDisplayMetrics(context).densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static String readJsonFromAssets(Context context, String path){

        try {
            String json;
            InputStream inputStream = context.getAssets().open(path);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)  view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)  view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    public static int[] calculateAge(String dateOfBirth) {

        int[] age = {0, 0, 0};

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Date birthDate = null;
        try {
            birthDate = format.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (birthDate == null){
            return age;
        }

        int years;
        int months;
        int days;

        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;

        //Get difference between months
        months = currMonth - birthMonth;

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0) {

            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {

            years--;
            months = 11;
        }

        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE)) {

            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);

        } else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {

            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;

        } else {

            days = 0;
            if (months == 12)
            {
                years++;
                months = 0;
            }
        }

        age[0] = years;
        age[1] = months;
        age[2] = days;

        return age;
    }
}
