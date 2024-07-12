package com.thezayin.analytics.helpers

import com.thezayin.analytics.events.AnalyticsEvent

interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)
}