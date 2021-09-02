package com.example.myapplication.ui.account.coindetai


import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityCoinDetailBinding
import com.example.myapplication.logic.model.bean.UserInfoVO

import com.example.myapplication.ui.base.BaseActivity

class CoinDetailActivity : BaseActivity<CoinViewModel, ActivityCoinDetailBinding>() {
    var adapter: CoinAdapter? = null
    var page: Int = 0
    var list: ArrayList<UserInfoVO>? = null
    override fun initData() {
        vm.setCheck(true)
    }

    override fun initListener() {
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    override fun initView() {
        list = ArrayList()
        adapter = CoinAdapter(mContext, list!!)
        v.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        v.mRecyclerView.adapter = adapter

    }

    override fun initVM() {
        vm.coins.observe(this, Observer {
            if (page == 0) list!!.clear()
            if (it != null) {
                list!!.addAll(it)
            }
            adapter!!.notifyDataSetChanged()
        })
    }

}