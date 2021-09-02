package com.example.myapplication.ui.adapter

import android.app.Activity
import com.example.myapplication.databinding.ItemHomeBinding
import com.example.myapplication.logic.model.bean.History
import com.example.myapplication.ui.base.BaseAdapter

/***
 * created by huangrui
 *@Date 2021/8/31 23:02
 */
class HistoryAdapter(context: Activity, listDatas: ArrayList<History>) :
    BaseAdapter<ItemHomeBinding, History>(context, listDatas) {
    override fun convert(v: ItemHomeBinding, t: History, position: Int) {
        v.tvTitle.text = t.title
        v.tvContent.text = t.desc
    }
}