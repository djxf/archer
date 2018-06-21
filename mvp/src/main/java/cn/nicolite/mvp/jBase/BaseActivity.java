package cn.nicolite.mvp.jBase;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.nicolite.mvp.R;
import cn.nicolite.mvp.utils.ActivityUtilsKt;
import cn.nicolite.mvp.utils.SlideUtilsKt;
import cn.nicolite.mvp.utils.StatusBarUtils;
import cn.nicolite.mvp.listener.ActivityLifeCycleListener;
import cn.nicolite.mvp.utils.LogUtils;


/**
 * Activity 基类 包含生命周期管理
 * 所有Activity都要继承此类
 * Created by nicolite on 17-9-6.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    protected final String TAG = getClass().getSimpleName();
    protected Context context;
    protected Activity activity;
    private ActivityLifeCycleListener lifeCycleListener;
    protected Unbinder unbinder;
    protected static final int SENSOR = 697;
    protected static final int PORTRAIT = 519;
    protected static final int LANDSCAPE = 539;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, TAG + "-->onCreate()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onCreate(savedInstanceState);
        }
        initConfig(savedInstanceState);
        setContentView(setLayoutId());
        unbinder = ButterKnife.bind(this);
        context = this;
        activity = this;
        Bundle bundle = getIntent().getExtras();
        initBundleData(bundle);
        doBusiness();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(TAG, TAG + "-->onStart()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(TAG, TAG + "-->onResume()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(TAG, TAG + "-->onPause()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(TAG, TAG + "-->onStop()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, TAG + "-->onDestroy()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onDestroy();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(TAG, TAG + "-->onRestart()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onRestart();
        }
    }

    /**
     * 初始化Activity配置,
     */
    protected void initConfig(Bundle savedInstanceState) {
        LogUtils.d(TAG, TAG + "-->initConfig()");
    }

    /**
     * 初始化Bundle参数
     *
     * @param bundle
     */
    protected void initBundleData(Bundle bundle) {
        LogUtils.d(TAG, TAG + "-->initBundleData()");
    }

    /**
     * 获取 xml layout
     */
    protected int setLayoutId() {
        LogUtils.d(TAG, TAG + "-->setLayoutId()");
        return R.layout.activity_default;
    }

    /**
     * 业务逻辑代码
     */
    protected void doBusiness() {
        LogUtils.d(TAG, TAG + "-->doBusiness()");
    }

    /**
     * 设置生命周期监听
     *
     * @param lifecycleListener
     */
    public void setOnLifeCycleListener(ActivityLifeCycleListener lifecycleListener) {
        this.lifeCycleListener = lifecycleListener;
    }

    /**
     * 页面跳转
     *
     * @param clazz
     */
    public void startActivity(Class<?> clazz) {
        ActivityUtilsKt.startActivity(context, clazz);
    }

    /**
     * 页面携带数据跳转
     *
     * @param clazz
     * @param bundle
     */
    public void startActivity(Class<?> clazz, Bundle bundle) {
        ActivityUtilsKt.startActivity(context, clazz, bundle);
    }

    /**
     * 包含回调的页面跳转
     *
     * @param clazz
     * @param requestCode
     */
    public void startActivityForResult(Class<?> clazz, int requestCode) {
        ActivityUtilsKt.startActivityForResult(activity, clazz, requestCode);
    }

    /**
     * 带动画的页面跳转
     *
     * @param clazz
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    public void startActivityWithOptions(Class<?> clazz, Bundle options) {
        ActivityUtilsKt.startActivityWithOptions(context, clazz, options);
    }

    /**
     * 带数据和动画的页面跳转
     *
     * @param clazz
     * @param bundle  数据
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    public void startActivity(Class<?> clazz, Bundle bundle, Bundle options) {
        ActivityUtilsKt.startActivity(context, clazz, bundle, options);
    }

    /**
     * 包含回调和数据的页面跳转
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        ActivityUtilsKt.startActivityForResult(activity, clazz, bundle, requestCode);
    }

    /**
     * 全屏
     */
    public void setAllowFullScreen() {
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void hideToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置屏幕旋转
     *
     * @param rotate SENSOR根据传感器自动旋转 PORTRAIT竖屏 LANDSCAPE横屏
     */
    public void setScreenRotate(int rotate) {
        switch (rotate) {
            case SENSOR:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                break;
            case PORTRAIT:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case LANDSCAPE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
        }
    }

    /**
     * 是否设置沉浸状态栏
     */
    public void setImmersiveStatusBar() {

        StatusBarUtils.setImmersiveStatusBar(this.getWindow());

    }

    /**
     * 使布局背景填充状态栏
     */
    public void setLayoutNoLimits() {
        // 布局背景填充状态栏
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }

    /**
     * 设置状态栏字体为深色
     *
     * @param isDeepColor
     */
    public void setDeepColorStatusBar(boolean isDeepColor) {
        if (isDeepColor) {
            StatusBarUtils.setDeepColorStatusBar(this.getWindow());
        }
    }

    /**
     * 是否设置滑动退出
     * 需要在主题中设置<item name="android:windowIsTranslucent">true</item>，否则将显示异常
     */
    public void setSlideExit() {
        SlideUtilsKt.setSlideExit(this);
    }

}
