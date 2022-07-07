package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.navigator.SignInNavigator
import java.lang.ref.WeakReference

class SignInViewModel(app : Application) : AndroidViewModel(app) {

    /**
     * WeakReference 를 이용해 SignInNavigator 인터페이스 참조 (SignInActivity 를 참조함)
     */
    var navigatorRef : WeakReference<SignInNavigator>? = null
    private val navigator get() = navigatorRef?.get()

    /**
     * 입력 받을 닉네임, 이메일 정보
     */
    val nickname = MutableLiveData("")
    val email = MutableLiveData("")

    /**
     * 참조한 객체의 함수
     * `fun startMainActivity(nickName : String, email : String)`를 이용해 MainActivity 실행,
     * next 버튼의 onClick 이벤트로 등록
     */
    fun startMain(){
        navigator?.startMainActivity(nickname.value!!,email.value!!)
    }
}