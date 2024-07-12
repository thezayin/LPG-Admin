package com.thezayin.analytics.events

data class AnalyticsEvent(
    val type: String,
    val extras: List<Param> = emptyList(),
) {
    // Standard analytics types.
    object Types {
        const val SCREEN_VIEW = "screen_view" // (extras: SCREEN_NAME)
        const val AD_IMPRESSION = "ad_impression"
    }

    /**
     * A key-value pair used to supply extra context to an
     * analytics event.
     *
     * @param key - the parameter key. Wherever possible use
     * one of the standard `ParamKeys`, however, if no suitable
     * key is available you can define your own as long as it is
     * configured in your backend analytics system (for example,
     * by creating a Firebase Analytics custom parameter).
     *
     * @param value - the parameter value.
     */
    data class Param(val key: String, val value: String)

    // Standard parameter keys.
    object ParamKeys {
        const val SCREEN_NAME = "screen_name"
        const val AD_TYPE = "ad_type"
    }
}