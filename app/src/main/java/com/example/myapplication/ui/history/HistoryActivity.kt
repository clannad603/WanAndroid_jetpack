package com.example.myapplication.ui.history

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityHistoryBinding
import com.example.myapplication.logic.model.bean.History
import com.example.myapplication.ui.adapter.HistoryAdapter
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.ui.content.ContentActivity

class HistoryActivity : BaseActivity<HistoryViewModel, ActivityHistoryBinding>() {
    var adapter: HistoryAdapter? = null
    var list: ArrayList<History>? = null
    override fun initVM() {
        vm.mHistory.observe(this, Observer {
            if (it != null) {
                list!!.addAll(it)
            }
            adapter!!.notifyDataSetChanged()
        })
    }

    override fun initListener() {
        adapter!!.itemClick {
            val intent = Intent(mContext, ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, adapter!!.listData[it].uri)
                putExtra(ContentActivity.TITLE, adapter!!.listData[it].title)
                putExtra(ContentActivity.DEC, adapter!!.listData[it].desc)
            }
            startActivity(intent)
        }
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    override fun initData() {
        vm.getHistory()
    }

    override fun initView() {
        list = ArrayList()
        adapter = HistoryAdapter(mContext, list!!)
        v.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        v.mRecyclerView.adapter = adapter
        v.mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mContext,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}