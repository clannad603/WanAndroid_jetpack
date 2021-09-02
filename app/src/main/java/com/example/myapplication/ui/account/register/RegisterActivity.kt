package com.example.myapplication.ui.account.register


import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.example.myapplication.logic.model.bean.RegisterInfo
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.utils.ToastUtil
import com.example.myapplication.utils.expand.setOnclickNoRepeat

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {
    override fun initData() {

    }

    override fun initListener() {
        v.btnRegister.setOnclickNoRepeat {
            attemptRegister()
        }
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    override fun initView() {

    }

    override fun initVM() {
        vm.loginErrorMessage.observe(this, Observer {
            if (it.isNotEmpty()) {
                ToastUtil.showLongToast(this, it)
            } else {
                ToastUtil.showLongToast(this, "注册成功")
            }
            finish()
        })
    }

    private fun attemptRegister() {
        val username = v.etUsername.text.toString()
        val password = v.etPassword.text.toString()
        val repassword = v.etRepassword.text.toString()
        if (repassword.isEmpty()) {
            ToastUtil.showLongToast(this, "请确认密码")
        } else if (password.isEmpty()) {
            ToastUtil.showLongToast(this, "请输入密码")
        } else if (password != repassword) {
            ToastUtil.showLongToast(this, "密码不一致")
        } else if (username.isEmpty()) {
            ToastUtil.showLongToast(this, "请输入用户名")

        } else {
            doRegister(username, password, repassword)
        }

    }

    private fun doRegister(username: String, password: String, repassword: String) {
        val registerInfo = RegisterInfo(username, password, repassword)
        vm.sendInfo(registerInfo)
    }

}