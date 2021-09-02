package com.example.myapplication.ui.mainview.fragmentcollect.project

import com.example.myapplication.databinding.FragmentProjectBinding
import com.example.myapplication.ui.adapter.CommonViewPagerAdapter
import com.example.myapplication.ui.base.BaseFragment
import java.util.*


class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {

    override fun initView() {
        v.tabLayout.setupWithViewPager(v.viewPager)
    }

    override fun initVM() {

        vm.proList.observe(this, {
            //得到标题集合
            val titles: MutableList<String> = ArrayList()

            for (i in it!!.indices) {
                titles.add(it[i].name)
            }

            //创建Fragment集合 并设置为ViewPager
            val commonViewPagerAdapter = CommonViewPagerAdapter(childFragmentManager, titles)
            for (i in titles.indices) {
                commonViewPagerAdapter.addFragment(ProjectChildFragment.newInstance(it!![i].id))
            }
            v.viewPager.adapter = commonViewPagerAdapter
            v.viewPager.currentItem = 0
        })

    }

    override fun initClick() {

    }

    override fun initData() {
        vm.setCheck(true)
    }
}