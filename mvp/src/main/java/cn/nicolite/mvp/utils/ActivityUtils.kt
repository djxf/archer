package cn.nicolite.mvp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle

/**
 * Created by nicolite on 2018/6/12.
 * email nicolite@nicolite.cn
 */

/**
 * 页面跳转
 */
fun startActivity(context: Context, clazz: Class<*>) {
    val intent = Intent(context, clazz)
    context.startActivity(intent)
}

/**
 * 页面携带数据跳转
 *
 * @param clazz
 * @param bundle
 */
fun startActivity(context: Context, clazz: Class<*>, bundle: Bundle?) {
    val intent = Intent()
    intent.setClass(context, clazz)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    context.startActivity(intent)
}

/**
 * 带动画的页面跳转
 *
 * @param clazz
 * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
 */
fun startActivityWithOptions(context: Context, clazz: Class<*>, options: Bundle?) {
    val intent = Intent()
    intent.setClass(context, clazz)
    if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        context.startActivity(intent, options)
    }
    context.startActivity(intent)
}

/**
 * 带数据和动画的页面跳转
 *
 * @param clazz
 * @param bundle  数据
 * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
 */
fun startActivity(context: Context, clazz: Class<*>, bundle: Bundle?, options: Bundle?) {
    val intent = Intent()
    intent.setClass(context, clazz)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        context.startActivity(intent, options)
    }
    context.startActivity(intent)
}

/**
 * 包含回调的页面跳转
 *
 * @param clazz
 * @param requestCode
 */
fun startActivityForResult(activity: Activity, clazz: Class<*>, requestCode: Int) {
    val intent = Intent()
    intent.setClass(activity, clazz)
    activity.startActivityForResult(intent, requestCode)
}

/**
 * 包含回调和数据的页面跳转
 *
 * @param clazz
 * @param bundle
 * @param requestCode
 */
fun startActivityForResult(activity: Activity, clazz: Class<*>, bundle: Bundle?, requestCode: Int) {
    val intent = Intent()
    intent.setClass(activity, clazz)
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    activity.startActivityForResult(intent, requestCode)
}