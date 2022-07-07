package com.example.email

import android.app.Application
import androidx.lifecycle.MutableLiveData

class App : Application() {
    companion object{
        val mailType  = MutableLiveData(0)
    }
}