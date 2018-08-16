package cn.nicolite.archer.view.custom

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


 class NoScrollViewPager(context: Context, attrs: AttributeSet? = null)
    :ViewPager(context, attrs){
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }
}