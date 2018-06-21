package cn.nicolite.mvp.jBase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.nicolite.mvp.R;
import cn.nicolite.mvp.utils.ActivityUtilsKt;
import cn.nicolite.mvp.listener.FragmentLifeCycleListener;
import cn.nicolite.mvp.utils.LogUtils;


/**
 * Fragment基类，所有Fragment都要继承此类
 * Created by nicolite on 17-10-13.
 */

public abstract class BaseFragment extends RxFragment {
    protected final String TAG = getClass().getSimpleName();
    protected Unbinder unbinder;
    private FragmentLifeCycleListener lifeCycleListener;
    protected Context context;
    protected BaseActivity activity;
    protected boolean isViewCreated;
    protected boolean isUIVisible;
    protected boolean isFirstVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.d(TAG, TAG + "-->setUserVisibleHint()");

        isUIVisible = isVisibleToUser;

        if (isViewCreated) {
            visibleToUser(isUIVisible, isFirstVisible);
            isFirstVisible = false;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.d(TAG, TAG + "-->onAttach()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onAttach(context);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, TAG + "-->onCreate()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onCreate(savedInstanceState);
        }
        isFirstVisible = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, TAG + "-->onCreateView()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(setLayoutId(), container, false);
        initConfig(savedInstanceState);

        Bundle arguments = getArguments();
        initArguments(arguments);

        context = getContext();

        unbinder = ButterKnife.bind(this, view);

        isViewCreated = true;
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(TAG, TAG + "-->onActivityCreated()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onActivityCreated(savedInstanceState);
        }
        activity = (BaseActivity) getActivity();

        doBusiness();

        if (isFirstFragment()) {
            visibleToUser(isUIVisible, isFirstVisible);
            isFirstVisible = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d(TAG, TAG + "-->onStart()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, TAG + "-->onResume()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, TAG + "-->onPause()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d(TAG, TAG + "-->onStop()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, TAG + "-->onDestroyView()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onDestroyView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, TAG + "-->onDestroy()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onDestroy();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d(TAG, TAG + "-->onDetach()");
        if (lifeCycleListener != null) {
            lifeCycleListener.onDetach();
        }
    }

    /**
     * 设置生命周期监听
     *
     * @param lifecycleListener
     */
    public void setOnLifeCycleListener(FragmentLifeCycleListener lifecycleListener) {
        this.lifeCycleListener = lifecycleListener;
    }


    /**
     * 初始化Fragment配置,
     */
    protected void initConfig(Bundle savedInstanceState) {
        LogUtils.d(TAG, TAG + "-->initConfig()");
    }

    /**
     * 初始化Bundle参数
     *
     * @param arguments
     */
    protected void initArguments(Bundle arguments) {
        LogUtils.d(TAG, TAG + "-->initArguments()");
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
     * fragment对用户可见
     *
     * @param isVisible      是否可见
     * @param isFirstVisible 是否第一次可见
     */
    protected void visibleToUser(boolean isVisible, boolean isFirstVisible) {
        LogUtils.d(TAG, TAG + "-->visibleToUser()");
    }

    /**
     * 是否为Attach到Activity中的第一个fragment
     *
     * @return
     */
    protected boolean isFirstFragment() {
        LogUtils.d(TAG, TAG + "-->isFirstFragment()");
        return false;
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
}
