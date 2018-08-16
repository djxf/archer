package cn.nicolite.archer

import android.app.Application
import cn.nicolite.mvp.utils.LogUtils


class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtils.debug = BuildConfig.DEBUG
    }
}