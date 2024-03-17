package peter.skydev.lolpatch.free.WebViews

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.ImageView
import com.bumptech.glide.Glide
import lolnews.skydev.peter.free.util.AdMobHelper
import lolnews.skydev.peter.free.util.Prefs
import org.jetbrains.anko.alert
import peter.skydev.lolpatch.free.R

class AllNewsWebView : Activity() {
    lateinit var webView: WebView

    lateinit var prefs: Prefs
    var server: String = ""
    var language: String = ""
    var patch: String = ""

    lateinit var adMob: AdMobHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_news_web_view)

        adMob = AdMobHelper(this)

        this.prefs = Prefs(this)

        this.language = prefs.lang
        this.server = prefs.server
        this.patch = prefs.patch

        this.webView = findViewById(R.id.webView)
        this.webView.settings.javaScriptEnabled = true
        this.webView.settings.loadsImagesAutomatically = true
        this.webView.settings.useWideViewPort = true
        this.webView.settings.loadWithOverviewMode = true
        this.webView.settings.setSupportZoom(true)
        this.webView.settings.builtInZoomControls = true
        this.webView.settings.displayZoomControls = false
        this.webView.settings.domStorageEnabled = true

        if (this.isNetworkConnected()) {
            this.webView.webViewClient = CustomWebViewClient(this)
            this.webView.loadUrl("https://leagueoflegends.com/" + getURLWithLanguage(language) + "/news/")
            val imageView = findViewById<ImageView>(R.id.gif) as ImageView
            Glide.with(this).load(R.drawable.loader).into(imageView)
        } else {
            alert("You need internet connection") {
                positiveButton("OK") { finish() }
            }.show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager// 1
        val networkInfo = connectivityManager.activeNetworkInfo // 2
        return networkInfo != null && networkInfo.isConnected // 3
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack()) {
            this.webView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!prefs.itemBought) {
            adMob.showAd()
        }
    }

    private fun getURLWithLanguage(language: String = "English (NA)"): String {
        var languageMap = mutableMapOf("English (NA)" to "en-us")
        languageMap.put("English (EUW)", "en-gb")
        languageMap.put("Deutsch", "de-de")
        languageMap.put("Español (EUW)", "es-es")
        languageMap.put("Français", "fr-fr")
        languageMap.put("Italiano", "it-it")
        languageMap.put("English (EUNE)", "en-pl")
        languageMap.put("Polski", "pl-pl")
        languageMap.put("Ελληνικά", "el-gr")
        languageMap.put("Română", "ro-ro")
        languageMap.put("Magyar", "hu-hu")
        languageMap.put("Čeština", "cs-cz")
        languageMap.put("Español (LATAM)", "es-mx")
        languageMap.put("Português", "pt-br")
        languageMap.put("日本語", "ja-jp")
        languageMap.put("Русский", "ru-ru")
        languageMap.put("Türkçe", "tr-tr")
        languageMap.put("English (OCE)", "en-au")
        languageMap.put("한국어", "ko-kr")

        return languageMap.getValue(language)
    }
}
