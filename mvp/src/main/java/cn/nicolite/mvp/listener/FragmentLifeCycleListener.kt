package cn.nicolite.mvp.listener

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Fragment生命周期监听
 * Created by nicolite on 17-11-4.
 */

interface FragmentLifeCycleListener: ActivityLifeCycleListener {

    fun onAttach(context: Context?)

    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)

    fun onActivityCreated(savedInstanceState: Bundle?)

    fun onDestroyView()

    fun onDetach()

}
