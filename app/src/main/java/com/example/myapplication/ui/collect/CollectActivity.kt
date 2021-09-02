package com.example.myapplication.ui.collect

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityCollectBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.ui.adapter.HomeAdapter
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.ui.content.ContentActivity

class CollectActivity : BaseActivity<CollectViewModel, ActivityCollectBinding>() {
    var adapter: HomeAdapter? = null
    var page: Int = 0
    var list: ArrayList<ArticleVO>? = null
    override fun initData() {
        vm.giveCheck(page)
    }

    override fun initListener() {
        adapter!!.itemClick {
            val intent = Intent(mContext, ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, adapter!!.listData[it].link)
                putExtra(ContentActivity.TITLE, adapter!!.listData[it].title)
                putExtra(ContentActivity.DEC, adapter!!.listData[it].desc)
            }
            startActivity(intent)
        }
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    override fun initView() {
        list = ArrayList()
        adapter = HomeAdapter(mContext, list!!)
        v.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        v.mRecyclerView.adapter = adapter
        v.mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mContext,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun initVM() {
        vm.articles.observe(this, Observer {
            if (page == 0) list!!.clear()
            if (it != null) {
                list!!.addAll(it)
            }
            adapter!!.notifyDataSetChanged()
        })
    }

}