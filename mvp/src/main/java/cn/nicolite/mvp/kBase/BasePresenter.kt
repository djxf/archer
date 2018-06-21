package cn.nicolite.mvp.kBase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.nicolite.mvp.listener.ActivityLifeCycleListener
import cn.nicolite.mvp.listener.FragmentLifeCycleListener
import cn.nicolite.mvp.utils.LogUtils
import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * Created by nicolite on 2018/5/20.
 * email nicolite@nicolite.cn
 */
abstract class BasePresenter<I, V>(iView: I, view: V) : ActivityLifeCycleListener, FragmentLifeCycleListener {
    protected val TAG = javaClass.simpleName
    private lateinit var iViewRef: Reference<I>
    private lateinit var viewRef: Reference<V>
    protected var context: Context? = null
    protected var activity: BaseActivity? = null
    protected var fragment: BaseFragment? = null

    init {
        attachIView(iView)
        attachView(view)
        setLifeCycleListener(view)
    }

    /**
     * 设置生命周期监听
     */
    private fun setLifeCycleListener(view: V) {
        when (view) {
            is BaseActivity -> view.setOnLifeCycleListener(this)
            is BaseFragment -> view.setOnLifeCycleListener(this)
        }
    }

    /**
     * 绑定IView
     */
    private fun attachIView(iView: I) {
        iViewRef = WeakReference<I>(iView)
    }

    /**
     * 绑定View
     */
    private fun attachView(view: V) {
        viewRef = WeakReference<V>(view)
    }

    /**
     * 解除IView绑定
     */
    private fun detachIView() {
        if (isIViewAttached()) {
            iViewRef.clear()
        }
    }

    /**
     * 解除View绑定
     */
    private fun detachView() {
        if (isViewAttached()) {
            viewRef.clear()
        }
    }

    /**
     * 获取IView
     */
    protected fun getIView(): I? {
        return iViewRef.get()
    }

    /**
     * 获取View
     */
    protected fun getView(): V? {
        return viewRef.get()
    }

    /**
     * 判断是否已经绑定IView
     */
    protected fun isIViewAttached(): Boolean {
        return iViewRef.get() != null
    }

    /**
     * 判断是否已经绑定View
     */
    protected fun isViewAttached(): Boolean {
        return viewRef.get() != null
    }

    override fun onCreate(saveInstanceState: Bundle?) {
        LogUtils.d(TAG, "$TAG-->onCreate()")
        val view = getView()
        if (view is BaseActivity) {
            activity = view
            context = activity
        }
    }

    override fun onStart() {
        LogUtils.d(TAG, "$TAG-->onStart()")
    }

    override fun onResume() {
        LogUtils.d(TAG, "$TAG-->onResume()")
    }

    override fun onPause() {
        LogUtils.d(TAG, "$TAG-->onPause()")
    }

    override fun onStop() {
        LogUtils.d(TAG, "$TAG-->onStop()")
    }

    override fun onDestroy() {
        LogUtils.d(TAG, "$TAG-->onDestroy()")
        if (getView() is BaseActivity) {
            detachIView()
            detachView()
            context = null
            activity = null
        }
    }

    override fun onRestart() {
        LogUtils.d(TAG, "$TAG-->onRestart()")
    }

    override fun onAttach(context: Context?) {
        LogUtils.d(TAG, "$TAG-->onAttach()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
        LogUtils.d(TAG, "$TAG-->onCreateView()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtils.d(TAG, "$TAG-->onActivityCreated()")
        val view = getView()
        if (view is BaseFragment) {
            fragment = view
            context = view.context
            activity = view.activity as BaseActivity
        }
    }

    override fun onDestroyView() {
        LogUtils.d(TAG, "$TAG-->onDestroyView()")
        if (getView() is BaseFragment) {
            detachIView()
            detachView()
            context = null
            activity = null
            fragment = null
        }
    }

    override fun onDetach() {
        LogUtils.d(TAG, "$TAG-->onDetach()")
    }

}