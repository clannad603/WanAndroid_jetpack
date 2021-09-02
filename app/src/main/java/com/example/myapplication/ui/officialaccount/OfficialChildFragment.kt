package com.example.myapplication.ui.officialaccount


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentOfficialChildBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.ui.adapter.ArticleListAdapter
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.content.ContentActivity

class OfficialChildFragment :
    BaseFragment<OfficialAccountViewModel, FragmentOfficialChildBinding>() {

    companion object {
        const val ID: String = "id"

        /**
         * 创建fragment
         */
        fun newInstance(id: Int): OfficialChildFragment {
            val officialChildFragment = OfficialChildFragment()
            val bundle = Bundle()
            bundle.putInt(ID, id)
            officialChildFragment.arguments = bundle
            return officialChildFragment
        }
    }

    private var mid: Int = 0
    var adapter: ArticleListAdapter? = null
    var page: Int = 0
    var list: ArrayList<ArticleVO>? = null
    override fun initClick() {
        adapter!!.itemClick {
            val intent = Intent(requireContext(), ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, adapter!!.listData[it].link)
            }
            startActivity(intent)
        }
    }

    override fun initData() {
        mid = arguments?.getInt(ID)!!
        vm.giveCheck(mid)
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