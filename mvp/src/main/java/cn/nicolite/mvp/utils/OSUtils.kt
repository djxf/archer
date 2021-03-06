package cn.nicolite.mvp.utils

import android.os.Build
import android.text.TextUtils
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by nicolite on 2018/7/15.
 * email nicolite@nicolite.cn
 * 系统辅助类，用于判断系统类型
 */
object OSUtils {
    private const val TAG = "OSUtils"
    private const val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private const val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"

    private const val KEY_EMUI_VERSION_CODE = "ro.build.version.emui"
    private const val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"


    fun isFlyme(): Boolean {
        return try {
            val method = Build::class.java.getMethod("hasSmartBar")
            method != null
        } catch (exception: Exception) {
            LogUtils.d(TAG, "Unable to find hasSmartBar \n${exception.message}")
            false
        }
    }

    fun isEMUI(): Boolean {
        return try {
            isPropertiesExist(KEY_EMUI_VERSION_CODE, KEY_EMUI_API_LEVEL)
        } catch (exception: Exception) {
            LogUtils.d(TAG, "Unable to find EMUI keys ${exception.message}")
            false
        }
    }

    fun isMIUI(): Boolean {
        return try {
            isPropertiesExist(KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME, KEY_MIUI_INTERNAL_STORAGE)
        } catch (exception: Exception) {
            LogUtils.d(TAG, "Unable to find MIUI keys ${exception.message}")

            false
        }
    }

    fun getMIUIVersion(): Int {
        var versionCode = 0
        try {
            val property = getProperty(KEY_MIUI_VERSION_NAME)
            if (property.isNotEmpty()) {
                val versionString = property.replace("V", "")
                        .replace("v", "")
                        .trim()
                if (TextUtils.isDigitsOnly(versionString)) {
                    versionCode = versionString.toInt()
                }
            }
        } catch (exception: Exception) {
            LogUtils.d(TAG, "get versionCode fail ${exception.message}")
        }

        return versionCode
    }

    private fun isPropertiesExist(vararg keys: String): Boolean {
        keys.forEach {
            if (getProperty(it).isNotEmpty()) {
                return true
            }
        }
        return false
    }

    private fun getProperty(name: String): String {
        var property = ""
        try {
            val process = Runtime.getRuntime().exec("getprop $name")
            val reader = BufferedReader(InputStreamReader(process.inputStream), 1024)
            reader.use {
                property = it.readLine()
            }
        } catch (exception: Exception) {
            LogUtils.d(TAG, "Unable to read prop $name \n${exception.message}")
        }
        return property
    }

}