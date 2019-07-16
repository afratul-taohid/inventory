package com.amirsons.inventory.utils

import android.os.CountDownTimer

object MyCountDownTimer {

    private const val format = "Timeout: %s Minute %s second"
    private var countDownTimer: CountDownTimer? = null

    fun run(second: Int, countDownListener: CountDownListener) {

        countDownTimer = object : CountDownTimer(second.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {

                val seconds = millisUntilFinished / 1000 //convert to seconds
                val minutes = seconds / 60 //convert to minutes
                val hours = minutes / 60 //convert to hours

                val strSecond = formatNumber(seconds)
                val strMinute = formatNumber(minutes)
                val strHour = formatNumber(hours)

                val formattedString = String.format(format, strMinute, strSecond)

                countDownListener.onTick(formattedString, strSecond, strMinute, strHour)
            }

            override fun onFinish() {
                countDownListener.onFinish()
            }

        }.start()
    }

    private fun formatNumber(value: Long): String {
        return if (value < 10) "0$value" else value.toString()
    }

    fun stopCountDown() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    interface CountDownListener {
        fun onTick(formattedString: String, second: String, minute: String, hour: String)
        fun onFinish()
    }
}
