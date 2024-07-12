package com.thezayin.lpgadmin.common.application

import android.app.Application
import com.thezayin.analytics.di.analyticsHelperModule
import com.thezayin.di.addProductModule
import com.thezayin.di.appModule
import com.thezayin.di.getUserOrdersModule
import com.thezayin.di.homeModule
import com.thezayin.di.productModule
import com.thezayin.di.productUpdateModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
            modules(homeModule)
            modules(productModule)
            modules(getUserOrdersModule)
            modules(productUpdateModule)
            modules(analyticsHelperModule)
            modules(addProductModule)
        }
    }
}