package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SettingViewModel(app : Application) : AndroidViewModel(app) {
    /**
     * textView 로 나타낼 닉네임, 이메일 정보
     */
    val nicknameText = MutableLiveData("")
    val emailText = MutableLiveData("")
}