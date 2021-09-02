package com.example.myapplication.ui.mainview

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.account.login.LoginActivity
import com.example.myapplication.ui.account.logout.LogoutActivity
import com.example.myapplication.ui.account.mine.MineActivity
import com.example.myapplication.ui.adapter.CommonViewPagerAdapter
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.ui.collect.CollectActivity
import com.example.myapplication.ui.history.HistoryActivity
import com.example.myapplication.ui.mainview.fragmentcollect.home.HomeFragment
import com.example.myapplication.ui.mainview.fragmentcollect.project.ProjectFragment
import com.example.myapplication.ui.mainview.fragmentcollect.qa.QaFragment
import com.example.myapplication.ui.mainview.fragmentcollect.square.SquareFragment
import com.example.myapplication.ui.officialaccount.OffficialActivity
import com.example.myapplication.ui.search.SearchActivity
import com.example.myapplication.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_mine.view.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    /**
     * Drawer关联Toolbar
     */
    private fun initActionBarDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            hometoolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    /**
     * 初始化Fragment
     */
    private fun initFragments() {
        val viewPagerAdapter = CommonViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(HomeFragment())
        viewPagerAdapter.addFragment(SquareFragment())
        viewPagerAdapter.addFragment(QaFragment())
        viewPagerAdapter.addFragment(ProjectFragment())
        view_pager.offscreenPageLimit = 1
        view_pager.adapter = viewPagerAdapter
    }

    override fun initData() {

    }

    override fun initListener() {
        /**
         * view_pager 滑动监听
         */
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottom_navigation.menu.getItem(position).isChecked = true
                //设置checked为true，但是不能触发ItemSelected事件，所以滑动时也要设置一下标题
                when (position) {
                    0 -> {
                        hometoolbar.title = "首页"
                    }
                    1 -> {
                        hometoolbar.title = "广场"
                    }
                    2 -> {
                        hometoolbar.title = "问答"
                    }
                    else -> {
                        hometoolbar.title = "项目"
                    }
                }
            }
        })
        /**
         * bottom_navigation 点击事件
         */
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    view_pager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_square -> {
                    view_pager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_qa -> {
                    view_pager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_project -> {
                    view_pager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }

    override fun initView() {
        /**
         * 侧边栏点击事件
         */
        initActionBarDrawer()
        initFragments()
        hometoolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.homeSearch -> startAnotherActivity(SearchActivity::class.java)
            }
            true
        }
        nav_view.setNavigationItemSelectedListener {
            // Handle navigation view item clicks here.
            when (it.itemId) {
                R.id.nav_home -> {
                    if (!isLogin) {
                        startAnotherActivity(LoginActivity::class.java)
                    } else {
                        ToastUtil.showLongToast(this, "已登陆")
                    }
                }
                /***
                 * 在这里跳转至其他活动，预计
                 */
                R.id.nav_collect -> {
                    startAnotherActivity(CollectActivity::class.java)
                }
                R.id.nav_logout -> {
                    startAnotherActivity(LogoutActivity::class.java)
                }
                R.id.mine -> {
                    startAnotherActivity(MineActivity::class.java)
                }
                R.id.officialAccount -> {
                    startAnotherActivity(OffficialActivity::class.java)
                }
                R.id.history -> {
                    startAnotherActivity(HistoryActivity::class.java)
                }
            }
            //关闭侧边栏
            drawer_layout.closeDrawer(GravityCompat.START)

            true
        }
    }

    override fun initVM() {

    }

}