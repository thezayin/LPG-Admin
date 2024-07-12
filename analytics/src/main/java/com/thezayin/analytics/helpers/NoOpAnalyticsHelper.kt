package com.thezayin.analytics.helpers

import com.thezayin.analytics.events.AnalyticsEvent


/**
 * NoOperationAnalyticsHelper; this implementation does nothing, it is useful for testing and previews
 */
class NoOpAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) = Unit
}