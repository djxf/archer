package cn.nicolite.mvp.jBase;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import cn.nicolite.mvp.listener.ActivityLifeCycleListener;
import cn.nicolite.mvp.listener.FragmentLifeCycleListener;
import cn.nicolite.mvp.utils.LogUtils;

/**
 * Presenter基类  所有Persenter都要继承此类
 * Created by nicolite on 17-10-13.
 */

public abstract class JBasePresenter<I, V> implements ActivityLifeCycleListener, FragmentLifeCycleListener {
    protected final String TAG = getClass().getSimpleName();
    private Reference<I> iViewRef;
    private Reference<V> viewRef;
    protected Context context;
    protected AppCompatActivity activity;
    protected Fragment fragment;

    public JBasePresenter(I iView, V view) {
        attachView(iView);
        attachActivity(view);
        setListener(view);
    }

    /**
     * 设置生命周期监听
     *
     * @param view
     */
    private void setListener(V view) {
        if (getView() != null) {
            if (view instanceof JBaseActivity) {
                ((JBaseActivity) getView()).setOnLifeCycleListener(this);
            } else if (view instanceof JBaseFragment) {
                ((JBaseFragment) getView()).setOnLifeCycleListener(this);
            }
        }
    }

    /**
     * 绑定View
     *
     * @param iView
     */
    private void attachView(I iView) {
        iViewRef = new WeakReference<I>(iView);
    }

    /**
     * 绑定Activity
     *
     * @param view
     */
    private void attachActivity(V view) {
        viewRef = new WeakReference<V>(view);
    }

    /**
     * 解除View绑定
     */
    private void detachIView() {
        if (isViewAttached()) {
            iViewRef.clear();
            iViewRef = null;
        }
    }

    /**
     * 解除Activity绑定
     */
    private void detachView() {
        if (isActivityAttached()) {
            viewRef.clear();
            viewRef = null;
        }
    }

    /**
     * 获取View
     *
     * @return
     */
    public I getiView() {
        if (iViewRef == null) {
            return null;
        }
        return iViewRef.get();
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public V getView() {
        if (viewRef == null) {
            return null;
        }
        return viewRef.get();
    }

    /**
     * 判断是否已经绑定View
     *
     * @return
     */
    public boolean isViewAttached() {
        return iViewRef != null && iViewRef.get() != null;
    }

    /**
     * 判定是否已经绑定Activity
     *
     * @return
     */
    public boolean isActivityAttached() {
        return viewRef != null && viewRef.get() != null;
    }


    @Override
    public void onCreate(Bundle saveInstanceState) {
        LogUtils.d(TAG, TAG + "-->onCreate()");
        V view = getView();
        if (view instanceof JBaseActivity) {
            activity = (AppCompatActivity) view;
            context = activity;
        }
    }

    @Override
    public void onStart() {
        LogUtils.d(TAG, TAG + "-->onStart()");
    }

    @Override
    public void onResume() {
        LogUtils.d(TAG, TAG + "-->onResume()");
    }

    @Override
    public void onPause() {
        LogUtils.d(TAG, TAG + "-->onPause()");
    }

    @Override
    public void onStop() {
        LogUtils.d(TAG, TAG + "-->onStop()");
    }

    @Override
    public void onDestroy() {
        LogUtils.d(TAG, TAG + "-->onDestroy()");
        if (getView() instanceof JBaseActivity) {
            detachIView();
            detachView();
            context = null;
            activity = null;
        }
    }

    @Override
    public void onRestart() {
        LogUtils.d(TAG, TAG + "-->onRestart()");
    }

    @Override
    public void onAttach(Context context) {
        LogUtils.d(TAG, TAG + "-->onAttach()");
    }

    @Override
    public void onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, TAG + "-->onCreateView()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, TAG + "-->onActivityCreated()");
        V view = getView();
        if (view instanceof JBaseFragment) {
            fragment = (Fragment) view;
            context = fragment.getContext();
            activity = (AppCompatActivity) fragment.getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        LogUtils.d(TAG, TAG + "-->onDestroyView()");
        if (getView() instanceof JBaseFragment) {
            detachIView();
            detachView();
            fragment = null;
            context = null;
            activity = null;
        }
    }

    @Override
    public void onDetach() {
        LogUtils.d(TAG, TAG + "-->onDetach()");
    }
}
