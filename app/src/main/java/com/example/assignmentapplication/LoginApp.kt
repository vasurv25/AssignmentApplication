package com.example.assignmentapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LoginApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}