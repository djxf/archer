package cn.nicolite.mvp.utils

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Created by nicolite on 2018/7/15.
 * email nicolite@nicolite.cn
 */
object StatusBarUtils {
    private val TAG = "StatusBarUtils"

    fun setImmersiveStatusBar(window: Window) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                //透明状态栏
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                //透明导航栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            }
            else -> LogUtils.d(TAG, "该系统版本不支持设置沉浸状态栏")
        }
    }

    fun setDeepColorStatusBar(window: Window) {
        when {
            OSUtils.isMIUI() -> {
                if (OSUtils.getMIUIVersion() < 9) {
                    miuiStatusBarLightMode(window)
                    LogUtils.d(TAG, "$TAG -> MIUI V8-")
                } else {
                    androidMStatusBarLightMode(window)
                    LogUtils.d(TAG, "$TAG -> MIUI V9+")
                }
            }
            OSUtils.isFlyme() -> {
                flymeStatusBarLightMode(window)
                LogUtils.d(TAG, "$TAG -> Flyme")
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                androidMStatusBarLightMode(window)
                LogUtils.d(TAG, "$TAG -> Android 6.0+")
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                // 透明状态栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                // 透明导航栏
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                LogUtils.d(TAG, "$TAG -> Android 4.0 < Android 6.0")
            }
        }
    }

    private fun flymeStatusBarLightMode(window: Window) {
        FlyMeStatusbarColorUtils.setStatusBarDarkIcon(window, true)
//        try {
//            val layoutParams = window.attributes
//            val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
//            val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
//            darkFlag.isAccessible = true
//            meizuFlags.isAccessible = true
//            val bit = darkFlag.getInt(null)
//            var value = meizuFlags.getInt(layoutParams)
//            value = value or bit
//            meizuFlags.setInt(layoutParams, value)
//            window.attributes = layoutParams
//        } catch (exception: Exception) {
//            LogUtils.d(TAG, "FlymeStatusBarLightMode: ${exception.message}")
//        }
    }

    private fun miuiStatusBarLightMode(window: Window) {
        try {
            val clazz = window.javaClass
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.java, Int::class.java)
            extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
        } catch (exception: Exception) {
            LogUtils.d(TAG, "MIUIStatusBarLightMode: ${exception.message}")
        }
    }

    private fun androidMStatusBarLightMode(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //必须清除这个flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.TRANSPARENT
        }
    }


}