package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.navigator.SignInNavigator
import java.lang.ref.WeakReference

class SignInViewModel(app : Application) : AndroidViewModel(app) {

    var navigatorRef : WeakReference<SignInNavigator>? = null
    private val navigator get() = navigatorRef?.get()

    val nickname = MutableLiveData("")
    val email = MutableLiveData("")

    fun startMain(){
        navigator?.startMainActivity(nickname.value!!,email.value!!)
    }
}