package com.example.myapplication.ui.officialaccount

import com.example.myapplication.databinding.FragmentOfficialAccountBinding
import com.example.myapplication.ui.adapter.CommonViewPagerAdapter
import com.example.myapplication.ui.base.BaseFragment
import java.util.*

class OfficialAccountFragment :
    BaseFragment<OfficialAccountViewModel, FragmentOfficialAccountBinding>() {
    override fun initClick() {
    }

    override fun initData() {
        vm.setCheck(true)
    }

    override fun initView() {
        v.tabLayout.setupWithViewPager(v.viewPager)
    }

    override fun initVM() {
        vm.officialList.observe(this, {
            //得到标题集合
            val titles: MutableList<String> = ArrayList()

            for (i in it!!.indices) {
                titles.add(it[i].name)
            }

            //创建Fragment集合 并设置为ViewPager
            val commonViewPagerAdapter = CommonViewPagerAdapter(childFragmentManager, titles)
            for (i in titles.indices) {
                commonViewPagerAdapter.addFragment(OfficialChildFragment.newInstance(it!![i].id))
            }
            v.viewPager.adapter = commonViewPagerAdapter
            v.viewPager.currentItem = 0
        })
    }
}