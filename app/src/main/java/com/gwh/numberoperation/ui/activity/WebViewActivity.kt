

package com.gwh.numberoperation.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.gwh.numberoperation.R
import com.gwh.numberoperation.databinding.ActivityWebViewBinding
import com.vip.base.activity.BaseActivity


/**
 * 展示网页共通界面。
 *
 * @author gwh
 * @since  2020/5/22
 */
class WebViewActivity : BaseActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {

    private var title: String = ""

    private var linkUrl: String = ""

    private var isShare: Boolean = false

    private var isTitleFixed: Boolean = false



    private var mode: Int = MODE_DEFAULT



    override fun initData() {

        initParams()
        setBarTitle(title)
    //    preloadInitVasSonic()
      //  initTitleBar()
        initWebView()
        mBinding.webView.loadUrl(linkUrl)
    }





    override fun onBackPressed() {
        if (mBinding.webView.canGoBack()) {
            mBinding.webView.goBack()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        mBinding.webView.destroy()

        super.onDestroy()
    }

    private fun initParams() {
        title = intent.getStringExtra(TITLE) ?: "详情"
        linkUrl = intent.getStringExtra(LINK_URL) ?: DEFAULT_URL
        isShare = intent.getBooleanExtra(IS_SHARE, false)
        isTitleFixed = intent.getBooleanExtra(IS_TITLE_FIXED, false)
        mode = intent.getIntExtra(PARAM_MODE, MODE_DEFAULT)
    }



    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        mBinding.webView.settings.run {
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            javaScriptEnabled = true
            mBinding.webView.removeJavascriptInterface("searchBoxJavaBridge_")
          //  intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis())
          //  mBinding.webView.addJavascriptInterface(SonicJavaScriptInterface(sonicSessionClient, intent), "sonic")
            allowContentAccess = true
            databaseEnabled = true
            domStorageEnabled = true
          //  setAppCacheEnabled(true)
            savePassword = false
            saveFormData = false
            useWideViewPort = true
            loadWithOverviewMode = true
            defaultTextEncodingName = "UTF-8"
            setSupportZoom(true)
        }
        mBinding.webView.webChromeClient = UIWebChromeClient()
        mBinding.webView.webViewClient = UIWebViewClient()
        mBinding.webView.setDownloadListener { url, _, _, _, _ ->
            // 调用系统浏览器下载
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    /**
     * 使用VasSonic框架提升H5首屏加载速度。
     */


    inner class UIWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {

            linkUrl = url
            super.onPageStarted(view, url, favicon)
          //  progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView, url: String) {
          //  logD(TAG, "onPageFinished >>> url:${url}")
            super.onPageFinished(view, url)
          //  sonicSession?.sessionClient?.pageFinish(url)
//            progressBar.visibility = View.INVISIBLE
        }

//        override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
////            if (sonicSession != null) {
////                val requestResponse = sonicSessionClient?.requestResource(url)
////                if (requestResponse is WebResourceResponse) return requestResponse
////            }
//            return null
//        }




    }

    inner class UIWebChromeClient : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
           // logD(TAG, "onReceivedTitle >>> title:${title}")
            if (!isTitleFixed) {
                title?.run {
                  //  this@WebViewActivity.title = this
                  //  tvTitle.text = this
                    setBarTitle(title)
                }
            }
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            mBinding.progress.progress = newProgress
            if (newProgress==100){
                mBinding.progress.visibility = View.GONE
            }else{
                if (mBinding.progress.visibility == View.GONE){
                    mBinding.progress.visibility = View.VISIBLE
                }
            }


            super.onProgressChanged(view, newProgress)
        }

    }

    companion object {
        const val TAG = "WebViewActivity"

        private const val TITLE = "title"

        private const val LINK_URL = "link_url"

        private const val IS_SHARE = "is_share"

        private const val IS_TITLE_FIXED = "isTitleFixed"

        const val MODE_DEFAULT = 0

        const val MODE_SONIC = 1

        const val MODE_SONIC_WITH_OFFLINE_CACHE = 2

        const val PARAM_MODE = "param_mode"

        const val DEFAULT_URL = "https://github.com"

      //  val DEFAULT_TITLE = GlobalUtil.appName

        /**
         * 跳转WebView网页界面
         *
         * @param context       上下文环境
         * @param title         标题
         * @param url           加载地址
         * @param isShare       是否显示分享按钮
         * @param isTitleFixed  是否固定显示标题，不会通过动态加载后的网页标题而改变。true：固定，false 反之。
         * @param mode          加载模式：MODE_DEFAULT 默认使用WebView加载；MODE_SONIC 使用VasSonic框架加载； MODE_SONIC_WITH_OFFLINE_CACHE 使用VasSonic框架离线加载
         */
        fun start(context: Context, title: String, url: String, isShare: Boolean = true, isTitleFixed: Boolean = true, mode: Int = MODE_SONIC) {
          //  url.preCreateSession()  //预加载url
            val intent = Intent(context, WebViewActivity::class.java).apply {
                putExtra(TITLE, title)
                putExtra(LINK_URL, url)
                putExtra(IS_SHARE, isShare)
                putExtra(IS_TITLE_FIXED, isTitleFixed)
                putExtra(PARAM_MODE, mode)
              //  putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis())
            }
            context.startActivity(intent)
        }
    }
}