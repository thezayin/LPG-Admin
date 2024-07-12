package com.thezayin.framework.remote

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

private const val INIT_ADS = "init_ads"

class RemoteConfig {
    private val default: Map<String, Any> = mapOf(
        INIT_ADS to true,
    )

    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        })
        setDefaultsAsync(default)
        fetchAndActivate().addOnCompleteListener {
            Log.d("RemoteConfig", "fetchAndActivate: ${all.mapValues { (_, v) -> v.asString() }}")
        }
    }
}
