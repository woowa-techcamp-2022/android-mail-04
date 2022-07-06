package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SettingViewModel(app : Application) : AndroidViewModel(app) {
    val nicknameText = MutableLiveData("")
    val emailText = MutableLiveData("")
}