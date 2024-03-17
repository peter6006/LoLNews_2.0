package lolnews.skydev.peter.free.util

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class AdMobHelper(activity: Activity) : AdListener() {
    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var activity: Activity
    private val TAG: String = "AdMobHelper"

    fun showAd() {
        mInterstitialAd?.show(this.activity)
    }

    override fun onAdLoaded() {
        // Code to be executed when an ad finishes loading.
        Log.i(TAG, "onAdLoaded")
    }

    override fun onAdOpened() {
        // Code to be executed when the ad is displayed.
        Log.i(TAG, "onAdOpened")
    }

    override fun onAdClosed() {
        // Code to be executed when when the interstitial ad is closed.
        Log.i(TAG, "onAdClosed")
    }

    init {
        Log.d(TAG, "Constructor")
        var adRequest = AdRequest.Builder().build()
        this.activity = activity
        InterstitialAd.load(
            this.activity,
            "ca-app-pub-4855450974262250/5129997665",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    Log.i(TAG, "onAdLoaded 2")
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    mInterstitialAd = null
                    Log.i(TAG, "onAdFailedToLoad 2 $p0")
                }
            })
    }
}
