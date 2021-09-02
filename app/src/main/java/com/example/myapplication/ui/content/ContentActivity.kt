package com.example.myapplication.ui.content


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityContentBinding
import com.example.myapplication.logic.model.bean.History
import com.example.myapplication.ui.base.BaseActivity
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : BaseActivity<ContentViewModel, ActivityContentBinding>() {


    companion object {
        const val WEB_URL = "url"
        const val TITLE = "title"
        const val DEC = "dec"
    }

    override fun initData() {
        var title = ""
        var url = ""
        var dec = ""
        intent?.extras?.getString(WEB_URL).let {
            webView.loadUrl(it)
            if (it != null) {
                url = it
            }
        }
        intent?.extras?.getString(DEC).let {
            if (it != null) {
                dec = it
            }
        }
        intent?.extras?.getString(TITLE).let {
            if (it != null) {
                title = it
            }
        }
        val history = History(title, url, dec)
        vm.insertHistory(history)
    }

    override fun initListener() {
        v.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        initWebView()
    }

    override fun initVM() {
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initWebView() {
        this.resources
            .getDrawable(R.drawable.color_progressbar).also { progressBar.progressDrawable = it }
        webView.run {
            webViewClient = object : WebViewClient() {

                override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                    super.onPageStarted(p0, p1, p2)
                    progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(p0: WebView?, p1: String?) {
                    super.onPageFinished(p0, p1)
                    progressBar.visibility = View.GONE
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(p0: WebView?, p1: Int) {
                    super.onProgressChanged(p0, p1)
                    progressBar.progress = p1
                    Log.e("browser", p1.toString())
                }

                override fun onReceivedTitle(p0: WebView?, p1: String?) {
                    super.onReceivedTitle(p0, p1)
                    p1?.let { v.toolbar.title = p1 }
                }

            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) webView.goBack()
        else super.onBackPressed()
    }
}