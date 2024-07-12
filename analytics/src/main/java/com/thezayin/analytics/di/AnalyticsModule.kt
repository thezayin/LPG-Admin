package com.thezayin.analytics.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.analytics.helpers.FirebaseAnalyticsHelper
import com.thezayin.analytics.helpers.StubAnalyticsHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsHelperModule = module {
    single { FirebaseAnalytics.getInstance(androidContext()) }
    single { FirebaseAnalyticsHelper(get()) }
    factoryOf(::FirebaseAnalyticsHelper) bind AnalyticsHelper::class
    factoryOf(::StubAnalyticsHelper) bind AnalyticsHelper::class
}