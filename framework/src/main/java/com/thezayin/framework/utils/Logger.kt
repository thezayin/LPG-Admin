package com.thezayin.framework.utils

import android.util.Log

open class Logger(private val component: String) {
    open fun e(message: String) = e(component, message)
    open fun w(message: String) = w(component, message)
    open fun v(message: String) = v(component, message)

    companion object {
        fun e(component: String, message: String) {
            logcatLine(6, component, message)
        }

        fun w(component: String, message: String) {
            logcatLine(5, component, message)
        }

        fun v(component: String, message: String) {
            logcatLine(2, component, message)
        }

        private fun logcatLine(priority: Int, component: String, message: String) {
            Log.println(priority, component, message)
        }
    }
}

class LoggerWithThread(val component: String) : Logger(component) {
    override fun e(message: String) = super.e(thread() + message)
    override fun w(message: String) = super.w(thread() + message)
    override fun v(message: String) = super.v(thread() + message)
    private fun thread() = "{${Thread.currentThread().id}} "
}