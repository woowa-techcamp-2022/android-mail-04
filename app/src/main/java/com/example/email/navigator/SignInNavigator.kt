package com.example.email.navigator

/**
 * SignInViewModel 이 WeakReference 를 이용해 참조할 인터페이스
 */
interface SignInNavigator {
    fun startMainActivity(nickName : String, email : String)
}