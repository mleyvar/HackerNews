package com.mplr.hackernews.logger

import android.util.Log

class Logger()  {

    fun e(tag: String, msg: String? = null, throwable: Throwable? = null) {
        Log.e(tag, msg, throwable)
        // in this point integrate crashlyticsLogger
    }

    fun i(tag: String, msg: String) {
        Log.i(tag, msg)
        // in this point integrate crashlyticsLogger
    }

    fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }

    fun d(tag: String, msg: String?, throwable: Throwable? = null) {
        Log.d(tag, msg, throwable)
    }
}