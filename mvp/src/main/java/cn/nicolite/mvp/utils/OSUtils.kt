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
    private val TAG = "OSUtils"
    private val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"

    private val KEY_EMUI_VERSION_CODE = "ro.build.version.emui"
    private val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"


    fun isFlyme(): Boolean {
        val method = Build::class.java.getMethod("hasSmartBar")
        return method != null
    }

    fun isEMUI(): Boolean {
        return isPropertiesExist(KEY_EMUI_VERSION_CODE, KEY_EMUI_API_LEVEL)
    }

    fun isMIUI(): Boolean {
        return isPropertiesExist(KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME, KEY_MIUI_INTERNAL_STORAGE)
    }

    fun getMIUIVersion(): Int {
        val property = getProperty(KEY_MIUI_VERSION_NAME)
        var versionCode = 0
        if (property.isNotEmpty()) {
            val versionString = property.replace("V", "")
                    .replace("v", "")
                    .trim()
            if (TextUtils.isDigitsOnly(versionString)) {
                versionCode = versionString.toInt()
            }
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