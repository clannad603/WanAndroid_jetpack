package com.example.myapplication.ui.account.mine

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityMineBinding
import com.example.myapplication.logic.model.Constant
import com.example.myapplication.ui.account.coindetai.CoinDetailActivity
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.utils.MyPreference
import com.example.myapplication.utils.ToastUtil

class MineActivity : BaseActivity<MineViewModel, ActivityMineBinding>() {
    private var userCoin: String by MyPreference(Constant.USER_COIN, " ")

    override fun initData() {
        if (isLogin) {
            vm.makeLogin(true)
        } else {
            ToastUtil.showLongToast(this, "未登录")
        }

    }

    override fun initListener() {
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
        v.btnCoin.setOnClickListener {
            startAnotherActivity(CoinDetailActivity::class.java)
        }
    }

    override fun initView() {

        v.textViewCoin.text = userCoin
        if (userCoin != " ") {
            initAnimate(userCoin.toInt())
        }

    }

    override fun initVM() {
        vm.userInfo.observe(this, Observer { userInfo ->
            v.textViewRank.text = userInfo?.rank
            v.textViewLevel.text = userInfo?.level.toString()

            v.textViewUserCode.text = userInfo?.userId.toString()
            if (userInfo?.coinCount.toString() != userCoin) {
                update()
            }
            userCoin = userInfo?.coinCount.toString()

        })

    }

    private fun initAnimate(int: Int) {
        ValueAnimator.ofInt(0, int).apply {
            duration = (1000 * (int / 100f)).toLong() + 500
            interpolator = LinearInterpolator()
            addUpdateListener {
                v.textViewCoin.text = (it.animatedValue as Int).toString()
            }
        }.start()
    }

    private fun update() {
        initAnimate(userCoin.toInt())
    }
}