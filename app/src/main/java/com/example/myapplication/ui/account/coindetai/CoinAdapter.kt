package com.example.myapplication.ui.account.coindetai

import android.app.Activity
import com.example.myapplication.databinding.ItemCodeBinding
import com.example.myapplication.logic.model.bean.UserInfoVO
import com.example.myapplication.ui.base.BaseAdapter

class CoinAdapter(context: Activity, listDatas: ArrayList<UserInfoVO>) :
    BaseAdapter<ItemCodeBinding, UserInfoVO>(context, listDatas) {
    override fun convert(v: ItemCodeBinding, t: UserInfoVO, position: Int) {
        v.tvCoinCount.text = t.coinCount.toString()
        v.tvDetail.text = t.reason
        v.tvTime.text = t.desc
    }
}
