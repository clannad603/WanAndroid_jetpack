package com.example.myapplication.ui.mainview.fragmentcollect.qa

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentQaBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.ui.adapter.HomeAdapter
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.content.ContentActivity
import com.example.myapplication.utils.ToastUtil

class QaFragment : BaseFragment<QaFragmentViewModel, FragmentQaBinding>() {
    var adapter: HomeAdapter? = null
    var page: Int = 0
    var list: ArrayList<ArticleVO>? = null
    override fun initClick() {
        adapter!!.itemClick {
            val intent = Intent(requireContext(), ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, adapter!!.listData[it].link)
                putExtra(ContentActivity.TITLE, adapter!!.listData[it].title)
                putExtra(ContentActivity.DEC, adapter!!.listData[it].desc)
            }
            startActivity(intent)
        }
        adapter!!.itemLongClick {
            if (adapter!!.listData[it].collect) {
                adapter!!.listData[it].collect = false
                vm.unCollectArticle(adapter!!.listData[it].id)
            } else {
                adapter!!.listData[it].collect = true
                vm.collectArticle(adapter!!.listData[it].id)
            }
            adapter!!.notifyDataSetChanged()
        }
    }

    override fun initData() {
        vm.giveCheck(page)
    }

    override fun initView() {
        list = ArrayList()
        adapter = HomeAdapter(mContext, list!!)
        v.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        v.mRecyclerView.adapter = adapter
        v.mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
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
        vm.collect.observe(this, Observer {
            ToastUtil.showShortToast(mContext, it.errorMsg)
        })
        vm.unCollect.observe(this, Observer {
            ToastUtil.showShortToast(mContext, it.errorMsg)
        })
    }
}