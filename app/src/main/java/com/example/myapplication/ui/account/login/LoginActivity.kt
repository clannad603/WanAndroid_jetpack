package com.example.myapplication.ui.account.login


import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.logic.model.bean.LoginInfo
import com.example.myapplication.ui.account.register.RegisterActivity
import com.example.myapplication.ui.base.BaseActivity
import com.example.myapplication.utils.ToastUtil
import com.example.myapplication.utils.expand.setOnclickNoRepeat

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {
    override fun initData() {

    }

    override fun initListener() {
        v.btnRegister.setOnclickNoRepeat {
            startAnotherActivity(RegisterActivity::class.java)
        }
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
        v.btnLogin.setOnclickNoRepeat {
            attemptLogin()
        }
    }

    override fun initView() {

    }

    override fun initVM() {
        vm.loginErrorMessage.observe(this, Observer {
            ToastUtil.showLongToast(this, it)
            finish()
        })
        vm.loginUser.observe(this, Observer {
            if (it == 0) {
                isLogin = true
            }
        })
    }

    private fun attemptLogin() {
        val username = v.editTextAccount.text.toString()
        val password = v.editTextTextPassword.text.toString()

        if (password.isEmpty()) {
            ToastUtil.showLongToast(this, "请输入密码")
        } else if (username.isEmpty()) {
            ToastUtil.showLongToast(this, "请输入用户名")

        } else {
            doLogin(username, password)
        }

    }

    private fun doLogin(username: String, password: String) {
        val loginInfo = LoginInfo(username, password)
        vm.sendInfo(loginInfo)
    }
}