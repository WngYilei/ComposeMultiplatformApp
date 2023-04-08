package com.xl.composemultiplatformapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @Author : wyl
 * @Date : 2023/4/3
 * Desc :
 */
class XUtils private constructor() {
    init {
        throw UnsupportedOperationException("unable to initialize")
    }

    companion object {
        private var application: Application? = null
        fun init(app: Application) {
            if (application == null) {
                application = app
            }
        }

        fun init(context: Context) {
            init(context.applicationContext as Application)
        }

        val app: Application?
            get() {
                if (application != null) {
                    return application
                }
                try {
                    @SuppressLint("PrivateApi") val activityThread =
                        Class.forName("android.app.ActivityThread")
                    val at = activityThread.getMethod("currentActivityThread").invoke(null)
                    val app = activityThread.getMethod("getApplication").invoke(at)
                        ?: throw NullPointerException("u should init first")
                    init(app as Application)
                    return application
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                throw NullPointerException("u should init first")
            }
    }
}