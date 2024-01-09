package com.moeez.banneradkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.moeez.banneradkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var mContext: Context
    private var adContainer: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        mContext = this

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("place test device id here"))
                .build())

    }

    private fun initializeBannerAd() {
        MobileAds.initialize(mContext, OnInitializationCompleteListener {
            Log.e("bannerAdTag", "onInitializationComplete: ")
        })
        val adRequest = AdRequest.Builder().build()

        binding?.bannerAdView?.loadAd(adRequest)
        binding?.bannerAdView?.adListener = object : AdListener() {
            override fun onAdClicked() {
                super.onAdClicked()
                Log.e("bannerAdTag", "onAdClicked: ")
            }

            override fun onAdClosed() {
                super.onAdClosed()
                Log.e("bannerAdTag", "onAdClosed: ")
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                Log.e("bannerAdTag", "onAdFailedToLoad: " + loadAdError.message)
                adContainer?.visibility = View.GONE
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                Log.e("bannerAdTag", "onAdLoaded: ")
                adContainer?.visibility = View.VISIBLE
            }

            override fun onAdOpened() {
                super.onAdOpened()
                Log.e("bannerAdTag", "onAdOpened: ")
            }

            override fun onAdImpression() {
                super.onAdImpression()
                Log.e("bannerAdTag", "onAdImpression: " )
            }
        }

    }

    companion object {
        private const val TAG = "BANNER_AD"
    }

}