package com.example.myapplication.ui.account.logout

import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityLogoutBinding
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.utils.ToastUtil
import com.example.myapplication.utils.expand.setOnclickNoRepeat

class LogoutActivity : BaseActivity<LogoutViewModel, ActivityLogoutBinding>() {
    override fun initData() {

    }

    override fun initListener() {
        v.btnLogout.setOnclickNoRepeat {
            if (isLogin) {
                vm.check(true)
            } else {
                ToastUtil.showLongToast(this, "未登录，退出失败")
            }
        }
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    override fun initView() {

    }

    override fun initVM() {
        vm.logoutErrorCode.observe(this, Observer {
            if (it == 0) {
                ToastUtil.showLongToast(this, "退出成功")
                isLogin = false
                finish()
            }
        })
    }

}