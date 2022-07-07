package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.App
import com.example.email.data.DummyDataUtil
import com.example.email.data.Mail

class MailViewModel(app : Application) : AndroidViewModel(app) {
    /**
     * textView 에 출력될 메일 타입 정보
     */
    val typeText = MutableLiveData("Primary")

    /**
     * recyclerView 로 나타낼 메일 목록
     */
    val mails = mutableListOf<Mail>()

    /**
     * 타입에 맞는 메일 목록을 가져올 함수
     * DummyDataUtil 로 부터 DummyData 를 가져옴
     */
    fun getMails(){
        val type = App.mailType.value ?: return

        mails.clear()
        mails.addAll(DummyDataUtil.getMails(type))

        when(type){
            0 -> typeText.value = "Primary"
            1 -> typeText.value = "Social"
            2 -> typeText.value = "Promotions"
        }
    }
}