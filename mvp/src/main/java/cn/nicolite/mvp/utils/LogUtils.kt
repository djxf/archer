package cn.nicolite.mvp.utils

import android.util.Log

/**
 * Created by nicolite on 17-6-24.
 * 用来控制Log输出，当发布正式版时讲LEVEL设置为NOTHING就不会输出任何日志，防止被他人看到重要信息，也可以设置为其他
 */

object LogUtils {
    private val VERBOSE = 1
    private val DEBUG = 2
    private val INFO = 3
    private val WARN = 4
    private val ERROR = 5
    private val NOTHING = 6

    @JvmField
    var LEVEL = VERBOSE
    @JvmField
    var debug = false

    @JvmStatic
    fun v(tag: String, msg: String) {
        if (debug && LEVEL <= VERBOSE) {
            Log.v(tag, msg)
        }
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        if (debug && LEVEL <= DEBUG) {
            Log.d(tag, msg)
        }
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        if (debug && LEVEL <= INFO) {
            Log.i(tag, msg)
        }
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        if (debug && LEVEL <= WARN) {
            Log.w(tag, msg)
        }
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        if (debug && LEVEL <= ERROR) {
            Log.e(tag, msg)
        }
    }

    @JvmStatic
    fun v(tag: String, msg: String, throwable: Throwable) {
        if (debug && LEVEL <= VERBOSE) {
            Log.v(tag, msg, throwable)
        }
    }

    @JvmStatic
    fun d(tag: String, msg: String, throwable: Throwable) {
        if (debug && LEVEL <= DEBUG) {
            Log.d(tag, msg, throwable)
        }
    }

    @JvmStatic
    fun i(tag: String, msg: String, throwable: Throwable) {
        if (debug && LEVEL <= INFO) {
            Log.i(tag, msg, throwable)
        }
    }

    @JvmStatic
    fun w(tag: String, msg: String, throwable: Throwable) {
        if (debug && LEVEL <= WARN) {
            Log.w(tag, msg, throwable)
        }
    }

    @JvmStatic
    fun e(tag: String, msg: String, throwable: Throwable) {
        if (debug && LEVEL <= ERROR) {
            Log.e(tag, msg, throwable)
        }
    }
}
