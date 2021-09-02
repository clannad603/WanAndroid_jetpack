package com.example.myapplication.ui.adapter

import android.app.Activity
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemHomeBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.ui.base.BaseAdapter

/***
 * created by huangrui
 *@Date 2021/8/31 16:18
 */
class HomeAdapter(context: Activity, listDatas: ArrayList<ArticleVO>) :
    BaseAdapter<ItemHomeBinding, ArticleVO>(context, listDatas) {
    override fun convert(v: ItemHomeBinding, t: ArticleVO, position: Int) {
        v.tvTitle.text = t.title
        if (listData[position].collect) {
            Glide.with(mContext).load(R.drawable.collect).into(v.imageCollect)
        } else {
            Glide.with(mContext).load(R.drawable.not_collect).into(v.imageCollect)
        }
        v.tvContent.text = t.desc
    }
}