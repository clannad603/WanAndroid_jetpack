package com.example.myapplication.ui.adapter

import android.app.Activity
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemArticleBinding
import com.example.myapplication.logic.model.bean.ArticleVO
import com.example.myapplication.ui.base.BaseAdapter

class ArticleListAdapter(context: Activity, listDatas: ArrayList<ArticleVO>) :
    BaseAdapter<ItemArticleBinding, ArticleVO>(context, listDatas) {
    override fun convert(v: ItemArticleBinding, t: ArticleVO, position: Int) {
        Glide.with(mContext).load(t.envelopePic).into(v.ivCover)
        v.tvTitle.text = t.title
        v.tvDes.text = t.desc
    }
}