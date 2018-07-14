package cn.nicolite.mvp.kBase

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.nicolite.mvp.R
import cn.nicolite.mvp.listener.FragmentLifeCycleListener
import cn.nicolite.mvp.utils.LogUtils
import cn.nicolite.mvp.utils.startActivity
import cn.nicolite.mvp.utils.startActivityForResult
import cn.nicolite.mvp.utils.startActivityWithOptions
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * Created by nicolite on 2018/5/20.
 * email nicolite@nicolite.cn
 */
abstract class KBaseFragment : RxFragment() {
    protected val TAG = javaClass.simpleName
    private var lifeCycleListener: FragmentLifeCycleListener? = null
    protected lateinit var mContext: Context
    protected lateinit var mActivity: AppCompatActivity
    protected lateinit var mFragment: Fragment
    protected var isViewCreated = false
    protected var isUserVisible = false
    protected var isFirstVisible = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        LogUtils.d(TAG, "$TAG-->setUserVisibleHint()")
        isUserVisible = true
        if (isViewCreated) {
            visibleToUser(isUserVisible, isFirstVisible)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        LogUtils.d(TAG, "$TAG-->onAttach()")
        lifeCycleListener?.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "$TAG-->onCreate()")
        lifeCycleListener?.onCreate(savedInstanceState)
        isFirstVisible = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.d(TAG, "$TAG-->onCreateView()")
        lifeCycleListener?.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(setLayoutId(), container, false)
        initConfig(savedInstanceState)
        initArguments(arguments)
        isViewCreated = true
        mFragment = this
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.d(TAG, "$TAG-->onActivityCreated()")
        lifeCycleListener?.onActivityCreated(savedInstanceState)
        mContext = context!!
        mActivity = getActivity() as AppCompatActivity
        doBusiness()

        if (isFirstFragment()) {
            visibleToUser(isUserVisible, isFirstVisible)
            isFirstVisible = false
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d(TAG, "$TAG-->onDestroyView()")
        lifeCycleListener?.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "$TAG-->onDestroy()")
        lifeCycleListener?.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d(TAG, "$TAG-->onDetach()")
        lifeCycleListener?.onDetach()
    }

    /**
     * 设置生命周期监听
     *
     * @param lifecycleListener
     */
    fun setOnLifeCycleListener(lifecycleListener: FragmentLifeCycleListener) {
        this.lifeCycleListener = lifecycleListener
    }


    /**
     * 初始化Fragment配置,
     */
    protected open fun initConfig(savedInstanceState: Bundle?) {
        LogUtils.d(TAG, "$TAG-->initConfig()")
    }

    /**
     * 初始化Bundle参数
     *
     * @param arguments
     */
    protected open fun initArguments(arguments: Bundle?) {
        LogUtils.d(TAG, "$TAG-->initArguments()")
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
     * fragment对用户可见
     *
     * @param isVisible      是否可见
     * @param isFirstVisible 是否第一次可见
     */
    protected open fun visibleToUser(isUserVisible: Boolean, isFirstVisible: Boolean) {
        LogUtils.d(TAG, "$TAG-->visibleToUser()")
    }

    /**
     * 是否为Attach到Activity中的第一个fragment
     *
     * @return
     */
    protected open fun isFirstFragment(): Boolean {
        LogUtils.d(TAG, "$TAG-->isFirstFragment()")
        return false
    }

    /**
     * 页面跳转
     */
    fun startActivity(clazz: Class<*>) {
        startActivity(mContext, clazz)
    }

    /**
     * 页面携带数据跳转
     *
     * @param clazz
     * @param bundle
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?) {
        startActivity(mContext, clazz, bundle)
    }

    /**
     * 包含回调的页面跳转
     *
     * @param clazz
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, requestCode: Int) {
        startActivityForResult(mActivity, clazz, requestCode)
    }

    /**
     * 带动画的页面跳转
     *
     * @param clazz
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivityWithOptions(clazz: Class<*>, options: Bundle?) {
        startActivityWithOptions(mContext, clazz, options)
    }

    /**
     * 带数据和动画的页面跳转
     *
     * @param clazz
     * @param bundle  数据
     * @param options ActivityOptionsCompat.makeSceneTransitionAnimation()
     */
    fun startActivity(clazz: Class<*>, bundle: Bundle?, options: Bundle?) {
        startActivity(mContext, clazz, bundle, options)
    }

    /**
     * 包含回调和数据的页面跳转
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(clazz: Class<*>, bundle: Bundle?, requestCode: Int) {
        startActivityForResult(mActivity, clazz, bundle, requestCode)
    }
}