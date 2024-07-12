package com.thezayin.analytics.helpers

import com.thezayin.analytics.events.AnalyticsEvent
import timber.log.Timber

private const val TAG = "StubAnalyticsHelper"

/**
 * An implementation of AnalyticsHelper just writes the events
 * to logcat. Used in builds where no analytics events
 * should be sent to a backend.
 */
class StubAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) {
        Timber.tag(TAG).d("Received analytics event: $event")
    }
}