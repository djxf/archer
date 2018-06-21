package cn.nicolite.mvp.utils

import android.app.Activity
import android.graphics.Color

import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrListener
import com.r0adkll.slidr.model.SlidrPosition

/**
 * Created by nicolite on 17-6-23.i
 * Activty活动退出配置
 */


/**
 * Slidr默认配置
 * @return
 */
private val slideConfig: SlidrConfig
    get() = SlidrConfig.Builder()
            // .primaryColor(getResources().getColor(R.color.primary)
            // .secondaryColor(getResources().getColor(R.color.secondary) // The % of the screen that counts as the edge, default 18%
            .position(SlidrPosition.LEFT)
            .sensitivity(1f)
            .scrimColor(Color.BLACK)
            .scrimStartAlpha(0.8f)
            .scrimEndAlpha(0f)
            .velocityThreshold(2400f)
            .distanceThreshold(0.25f)
            .edge(true)
            .edgeSize(0.18f)
            .listener(object : SlidrListener {
                override fun onSlideStateChanged(state: Int) {

                }

                override fun onSlideChange(percent: Float) {

                }

                override fun onSlideOpened() {

                }

                override fun onSlideClosed() {

                }
            })
            .build()

/**
 * 绑定滑动退出，使用默认配置
 * @param activity
 */
fun setSlideExit(activity: Activity) {
    Slidr.attach(activity, slideConfig)
}

/**
 * 绑定滑动退出，自定义配置
 * @param activity
 * @param slideConfig
 */
fun setSlideExit(activity: Activity, slideConfig: SlidrConfig) {
    Slidr.attach(activity, slideConfig)
}

