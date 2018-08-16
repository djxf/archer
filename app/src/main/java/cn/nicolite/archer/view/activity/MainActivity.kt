package cn.nicolite.archer.view.activity

import android.support.v4.app.Fragment
import cn.nicolite.archer.R
import cn.nicolite.archer.kBase.BaseActivity
import cn.nicolite.archer.view.adapter.TabAdapter
import cn.nicolite.archer.view.fragment.Fragment_1
import cn.nicolite.archer.view.fragment.Fragment_2
import cn.nicolite.archer.view.fragment.Fragment_3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val fragmentList: List<Fragment>
        get() = listOf(Fragment_1(), Fragment_2(), Fragment_3())

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun doBusiness() {
        super.doBusiness()
        viewPager.apply {
            adapter = TabAdapter(supportFragmentManager, fragmentList)
            offscreenPageLimit = 3
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_bottom_1 -> {
                    viewPager.currentItem = 0
                }
                R.id.main_bottom_2 -> {
                    viewPager.currentItem = 1
                }
                R.id.main_bottom_3 -> {
                    viewPager.currentItem = 2
                }
            }
            true
        }

    }


}
