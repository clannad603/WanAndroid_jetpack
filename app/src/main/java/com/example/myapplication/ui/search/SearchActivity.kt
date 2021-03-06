package com.example.myapplication.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySearchBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.logic.model.bean.HotKeyText
import com.example.myapplication.logic.model.bean.SearchInfo
import com.example.myapplication.ui.adapter.HomeAdapter
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.ui.content.ContentActivity
import com.example.myapplication.utils.ToastUtil
import kotlinx.android.synthetic.main.toolbar.*

class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {
    private lateinit var mAdapter: HomeAdapter
    private lateinit var mKey: String
    private var page: Int = 0
    var list: ArrayList<ArticleVO>? = null
    override fun initData() {
        vm.getHotKey(true)
        vm.getMyHotKey()
    }

    override fun initListener() {
        mAdapter.itemClick {
            val intent = Intent(mContext, ContentActivity::class.java).apply {
                putExtra(ContentActivity.WEB_URL, mAdapter.listData[it].link)
                putExtra(ContentActivity.DEC, mAdapter.listData[it].desc)
                putExtra(ContentActivity.TITLE, mAdapter.listData[it].title)
            }
            startActivity(intent)
        }
        mAdapter.itemLongClick {
            if (mAdapter.listData[it].collect) {
                mAdapter.listData[it].collect = false
                vm.unCollectArticle(mAdapter.listData[it].id)
            } else {
                mAdapter.listData[it].collect = true
                vm.collectArticle(mAdapter.listData[it].id)
            }
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        list = ArrayList()
        mAdapter = HomeAdapter(mContext, list!!)
        v.mRecyclerView.layoutManager = LinearLayoutManager(mContext)
        v.mRecyclerView.adapter = mAdapter
        v.mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mContext,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun initVM() {
        vm.collect.observe(this, Observer {
            ToastUtil.showShortToast(mContext, it.errorMsg)
        })
        vm.unCollect.observe(this, Observer {
            ToastUtil.showShortToast(mContext, it.errorMsg)
        })
        vm.articles.observe(this, Observer {
            if (page == 0) list!!.clear()
            if (it != null) {
                list!!.addAll(it)
            }
            mAdapter.notifyDataSetChanged()
            v.mFlowLayout.visibility = View.GONE
            v.mLocalFlowLayout.visibility = View.GONE
            v.textView.visibility = View.GONE
            v.textView2.visibility = View.GONE
            v.mRecyclerView.visibility = View.VISIBLE

        })
        vm.hotkey.observe(this, Observer {

            it.data?.forEach { hotkey ->
                val textView = LayoutInflater.from(this)
                    .inflate(R.layout.search_label_tv, v.mFlowLayout, false) as TextView
                textView.apply {
                    text = hotkey.name
                }
                v.mFlowLayout.addView(textView)
                textView.setOnClickListener {
                    vm.giveSearch(SearchInfo(page, hotkey.name))
                }
            }
        })
        vm.mHotKey.observe(this, Observer {

            it.forEach { hotkey ->
                val textView = LayoutInflater.from(this)
                    .inflate(R.layout.search_label_tv, v.mFlowLayout, false) as TextView
                textView.apply {
                    text = hotkey.text
                }
                v.mLocalFlowLayout.addView(textView)
                textView.setOnClickListener {
                    vm.giveSearch(SearchInfo(page, hotkey.text))
                }
            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //??????menu??????
        menuInflater.inflate(R.menu.menu_search, menu)

        //??????SearchView?????????????????????
        val searchItem = menu.findItem(R.id.action_search)
        val mSearchView = searchItem.actionView as SearchView

        //???????????????????????????????????????
        mSearchView.setIconifiedByDefault(true)
        //???????????????????????????????????????????????????????????????
        mSearchView.isSubmitButtonEnabled = true
        //????????????????????????????????????
        mSearchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        //????????????????????????false????????????
        mSearchView.isIconified = true
        //????????????
        mSearchView.isFocusable = true
        mSearchView.requestFocusFromTouch()
        //???????????????
        mSearchView.queryHint = "??????????????????"
        //???????????????????????????
        val editText = mSearchView.findViewById<EditText>(R.id.search_src_text)
        editText.setHintTextColor(ContextCompat.getColor(this, R.color.color_DCDCDC))
        editText.setTextColor(ContextCompat.getColor(this, R.color.white))

        // ????????????????????????
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // ?????????????????????????????????????????????????????????
            override fun onQueryTextSubmit(query: String): Boolean {

                mKey = query
                page = 0 //?????????????????????????????????????????????
                //????????????
                val searchInfo = SearchInfo(page, mKey)
                val hotKeyText = HotKeyText(mKey)
                vm.insertHotKey(hotKeyText)
                vm.giveSearch(searchInfo)

                //???????????????????????????
                mSearchView.clearFocus()
                return false
            }

            // ???????????????????????????????????????
            override fun onQueryTextChange(newText: String): Boolean {
                //do something
                //??????????????????????????????????????????????????????????????????
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}