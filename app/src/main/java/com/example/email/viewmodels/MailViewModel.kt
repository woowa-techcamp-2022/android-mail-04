package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.App
import com.example.email.data.DummyDataUtil
import com.example.email.data.Mail

class MailViewModel(app : Application) : AndroidViewModel(app) {
    val typeText = MutableLiveData("Primary")

    val mails = mutableListOf<Mail>()

    fun getMails(){
        val type = App.mailType.value ?: return
        mails.clear()
        mails.addAll(DummyDataUtil.getMails(type))
        when(type){
            0 -> {
                typeText.value = "Primary"
            }
            1 -> {
                typeText.value = "Social"
            }
            2 -> {
                typeText.value = "Promotions"
            }
        }
    }
}