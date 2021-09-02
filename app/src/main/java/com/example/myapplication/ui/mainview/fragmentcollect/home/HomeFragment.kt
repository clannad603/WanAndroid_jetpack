package com.example.myapplication.ui.mainview.fragmentcollect.home

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.logic.model.bean.BannerVO
import com.example.myapplication.ui.adapter.BannerAdapter
import com.example.myapplication.ui.adapter.HomeAdapter
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.content.ContentActivity
import com.example.myapplication.utils.ToastUtil


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    var adapter: HomeAdapter? = null
    var page: Int = 0
    var list: ArrayList<ArticleVO>? = null
    var bannerAdapter: BannerAdapter? = null
    var bannerList: ArrayList<BannerVO>? = null

    override fun initView() {
        list = ArrayList()
        adapter = HomeAdapter(mContext, list!!)
        v.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        v.mRecyclerView.adapter = adapter
        bannerList = ArrayList()
        bannerAdapter = BannerAdapter(mContext, bannerList!!)
        v.mBanner.adapter = bannerAdapter
        v.mSwipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light
        )
        v.mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )

        )

    }

    //    private fun initSwipeRefreshLayout() {
//        .swipeRefresh.setColorSchemeResources(
//                android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light
//        )
//       .swipeRefresh.setOnRefreshListener {
//            .swipeRefresh.postDelayed({
//                mCurrentPage = 0v
//                mViewModel.getCollectList(mCurrentPage)
//                mBinding.swipeRefresh.isRefreshing = false
//            }, 1000)
//        }
//    }
    override fun initVM() {
        vm.articles.observe(this, Observer {
            if (page == 0) list!!.clear()
            if (it != null) {
                list!!.addAll(it)
            }
            adapter!!.notifyDataSetChanged()
        })
        vm.banner.observe(this, Observer {
            if (it != null) {
                bannerList?.addAll(it)
            }
            bannerAdapter?.notifyDataSetChanged()
        })
        vm.collect.observe(this, Observer {
            ToastUtil.showShortToast(mContext, it.errorMsg)
        })
        vm.unCollect.observe(this, Observer {
            ToastUtil.showShortToast(mContext, it.errorMsg)
        })
    }

    override fun initClick() {
        adapter!!.itemClick {
            val intent = Intent(requireContext(), ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, adapter!!.listData[it].link)
                putExtra(ContentActivity.TITLE, adapter!!.listData[it].title)
                putExtra(ContentActivity.DEC, adapter!!.listData[it].desc)
            }
            startActivity(intent)
        }
        bannerAdapter!!.itemClick {
            val intent = Intent(requireContext(), ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, bannerAdapter!!.listData[it].url)

            }
            startActivity(intent)
        }
        v.mSwipeRefreshLayout.setOnRefreshListener {
            page++
            vm.giveCheck(page)
            val mRefresh = Runnable { v.mSwipeRefreshLayout.isRefreshing = false }
            v.mSwipeRefreshLayout.postDelayed(mRefresh, 2000)
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
        vm.getBanner(true)
    }

}

