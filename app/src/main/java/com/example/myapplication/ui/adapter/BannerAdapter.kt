package com.example.myapplication.ui.adapter

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentBannerBinding
import com.example.myapplication.logic.model.bean.BannerVO
import com.example.myapplication.ui.base.BaseAdapter

/***
 * created by huangrui
 *@Date 2021/8/31 14:20
 */
class BannerAdapter(context: Activity, list: ArrayList<BannerVO>) :
    BaseAdapter<FragmentBannerBinding, BannerVO>(context, list) {

    override fun convert(v: FragmentBannerBinding, t: BannerVO, position: Int) {
        v.root.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.MATCH_PARENT
        )
        Glide.with(mContext).load(t.imagePath).into(v.mBannerPic)
    }
}