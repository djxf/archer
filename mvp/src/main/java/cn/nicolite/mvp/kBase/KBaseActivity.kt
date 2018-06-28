package cn.nicolite.mvp.kBase

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import cn.nicolite.mvp.R
import cn.nicolite.mvp.listener.ActivityLifeCycleListener
import cn.nicolite.mvp.utils.*
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by nicolite on 2018/5/20.
 * email nicolite@nicolite.cn
 */
abstract class KBaseActivity : RxAppCompatActivity() {
    protected val TAG = javaClass.simpleName
    protected lateinit var context: Context
    protected lateinit var activity: AppCompatActivity
    private var lifeCycleListener: ActivityLifeCycleListener? = null

    companion object {
        protected const val SENSOR = 697
        protected const val PORTRAIT = 519
        protected const val LANDSCAPE = 539
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "$TAG-->onCreate()")
        lifeCycleListener?.onCreate(savedInstanceState)
        initConfig(savedInstanceState)
        setContentView(setLayoutId())
        context = this
        activity = this
        initBundleData(intent.extras)
        doBusiness()
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "$TAG-->onStart()")
        lifeCycleListener?.onStart()
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "$TAG-->onResume()")
        lifeCycleListener?.onResume()
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "$TAG-->onPause()")
        lifeCycleListener?.onPause()
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "$TAG-->onStop()")
        lifeCycleListener?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "$TAG-->onDestroy()")
        lifeCycleListener?.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.d(TAG, "$TAG-->onRestart()")
        lifeCycleListener?.onRestart()
    }

    /**
     * 初始化Activity配置,
     */
    protected open fun initConfig(savedInstanceState: Bundle?) {
        LogUtils.d(TAG, "$TAG-->initConfig()")
    }

    /**
     * 初始化Bundle参数
     */
    protected open fun initBundleData(bundle: Bundle?) {
        LogUtils.d(TAG, "$TAG-->initBundleData()")
    }

    /**
     * 获取 xml layout
     */
    protected open fun setLayoutId(): Int {
        LogUtils.d(TAG, "$TAG-->setLayoutId()")
        return R.layout.layout_default
    }

    /**
     * 业务逻辑代码
     */
    protected open fun doBusiness() {
        LogUtils.d(TAG, "$TAG-->doBusiness()")
    }

    /**
     * 设置生命周期监听
     */
    fun setOnLifeCycleListener(lifecycleListener: ActivityLifeCycleListener) {
        this.lifeCycleListener = lifecycleListener
    }

    /**
     * 页面跳转
     */
    fun startActivity(clazz: Class<*>) {
        startActivity(context, clazz)
    }

    /**
     * 页面携带数据跳转
     *
     * @param clazz
     * @param bundle
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?) {
        startActivity(context, clazz, bundle)
    }

    /**
     * 包含回调的页面跳转
     *
     * @param clazz
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, requestCode: Int) {
        startActivityForResult(activity, clazz, requestCode)
    }

    /**
     * 带动画的页面跳转
     *
     * @param clazz
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivityWithOptions(clazz: Class<*>, options: Bundle?) {
        startActivityWithOptions(context, clazz, options)
    }

    /**
     * 带数据和动画的页面跳转
     *
     * @param clazz
     * @param bundle  数据
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?, options: Bundle?) {
        startActivity(context, clazz, bundle, options)
    }

    /**
     * 包含回调和数据的页面跳转
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, bundle: Bundle?, requestCode: Int) {
        startActivityForResult(activity, clazz, bundle, requestCode)
    }


    fun setPixelFormat() {
        this.window.setFormat(PixelFormat.TRANSLUCENT)
    }

    /**
     * 是否允许全屏
     */
    fun setFullScreen() {
        this.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun hideToolBar() {
        val actionBar = supportActionBar
        actionBar?.hide()
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置屏幕旋转
     *
     * @param rotate SENSOR根据传感器自动旋转 PORTRAIT竖屏 LANDSCAPE横屏
     */
    fun setScreenRotate(rotate: Int) {
        when (rotate) {
            SENSOR -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
            PORTRAIT -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            LANDSCAPE -> requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    /**
     * 是否设置沉浸状态栏
     *
     * @param isSetStatusBar
     */
    fun setImmersiveStatusBar() {
        StatusBarUtils.setImmersiveStatusBar(this.window)
    }

    /**
     * 使布局背景填充状态栏
     */
    fun setLayoutNoLimits() {
        // 布局背景填充状态栏 与键盘监听冲突
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    /**
     * 设置状态栏字体为深色
     */
    fun setDeepColorStatusBar() {
        StatusBarUtils.setDeepColorStatusBar(this.window)
    }

}