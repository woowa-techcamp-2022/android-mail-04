package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SignInViewModel(app : Application) : AndroidViewModel(app) {
    val nickname = MutableLiveData("")
    val email = MutableLiveData("")
}