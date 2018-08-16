package cn.nicolite.archer.view.fragment

import cn.nicolite.archer.R
import cn.nicolite.archer.kBase.BaseFragment
import kotlinx.android.synthetic.main.fragment_test.*


class Fragment_1:BaseFragment() {
    override fun setLayoutId(): Int {
        return R.layout.fragment_test
    }

    override fun doBusiness() {
        super.doBusiness()
        textView.text = "fragment_1"
    }
}