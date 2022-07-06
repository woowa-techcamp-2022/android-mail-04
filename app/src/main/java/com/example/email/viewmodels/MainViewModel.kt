package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.data.Mail

class MainViewModel(app : Application) : AndroidViewModel(app) {
    val mails = mutableListOf<Mail>()
    var drawerNavPosition = 0 // navigation drawer position
    var navPosition = 0 // side, bottom nav position
    val typeText = MutableLiveData("primary")

    fun getPrimaryMails() {
        mails.clear()
        (1..10).forEach {
            mails.add(Mail("","Sender$it","primary$it","primaryprimaryprimaryprimaryprimary","primary"))
        }
    }

    fun getSocialMails() {
        mails.clear()
        (1..10).forEach {
            mails.add(Mail("","Sender$it","social$it","socialsocialsocialsocialsocialsocial","social"))
        }
    }

    fun getPromotionMails() {
        mails.clear()
        (1..10).forEach {
            mails.add(Mail("","Sender$it","promotions$it","promotionspromotionspromotionspromotionspromotions","promotions"))
        }
    }
}