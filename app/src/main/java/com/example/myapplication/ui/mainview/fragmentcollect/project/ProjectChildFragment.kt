package com.example.myapplication.ui.mainview.fragmentcollect.project

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentChildBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.logic.model.bean.ProjectChildInfo
import com.example.myapplication.ui.adapter.ArticleListAdapter
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.content.ContentActivity

class ProjectChildFragment : BaseFragment<ProjectChildViewModel, FragmentChildBinding>() {
    companion object {
        const val CID: String = "cid"

        /**
         * 创建fragment
         */
        fun newInstance(cid: Int): ProjectChildFragment {
            val projectChildFragment = ProjectChildFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            projectChildFragment.arguments = bundle
            return projectChildFragment
        }
    }

    private var mCid: Int = 0
    var adapter: ArticleListAdapter? = null
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
    }

    override fun initData() {
        mCid = arguments?.getInt(CID)!!
        val projectChildInfo = ProjectChildInfo(page, mCid)
        vm.giveCheck(projectChildInfo)
    }

    override fun initView() {
        list = ArrayList()
        adapter = ArticleListAdapter(mContext, list!!)
        v.recyclerView.layoutManager = LinearLayoutManager(mContext)
        v.recyclerView.adapter = adapter
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