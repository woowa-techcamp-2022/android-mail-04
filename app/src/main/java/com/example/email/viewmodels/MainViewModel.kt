package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.R
import com.example.email.data.Mail

class MainViewModel(app : Application) : AndroidViewModel(app) {
    val mails = mutableListOf<Mail>()
    var drawerNavPosition = 0 // navigation drawer position
    var navPosition = MutableLiveData(R.id.item_mail) // side, bottom nav position
    val typeText = MutableLiveData("Primary")

    fun getMails(){
        when(drawerNavPosition){
            0 -> {
                getPrimaryMails()
                typeText.value = "Primary"
            }
            1 -> {
                getSocialMails()
                typeText.value = "Social"

            }
            2 -> {
                getPromotionMails()
                typeText.value = "Promotions"
            }
        }
    }

    private fun getPrimaryMails() {
        mails.clear()
        (1..5).forEach {
            mails.add(Mail("","Sender$it","primary$it","primaryprimaryprimaryprimaryprimary","primary"))
        }
        mails.add(Mail("","철수","ㅇㅇㅇ","primaryprimaryprimaryprimaryprimary","primary"))
        mails.add(Mail("","영희","ㅇㅇㅇ","primaryprimaryprimaryprimaryprimary","primary"))
        mails.add(Mail("","민수","ㅇㅇㅇ","primaryprimaryprimaryprimaryprimary","primary"))
    }

    private fun getSocialMails() {
        mails.clear()
        (1..5).forEach {
            mails.add(Mail("","Sender$it","social$it","socialsocialsocialsocialsocialsocial","social"))
        }
    }

    private fun getPromotionMails() {
        mails.clear()
        (1..5).forEach {
            mails.add(Mail("","Sender$it","promotions$it","promotionspromotionspromotionspromotionspromotions","promotions"))
        }
    }
}