package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.App
import com.example.email.data.Mail

class MailViewModel(app : Application) : AndroidViewModel(app) {
    val mails = mutableListOf<Mail>()
    val typeText = MutableLiveData("Primary")

    private val primaryMails = mutableListOf<Mail>()
    private val socialMails = mutableListOf<Mail>()
    private val promotionMails = mutableListOf<Mail>()

    fun getMails(){
        val type = App.mailType.value ?: return
        when(type){
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
        if (primaryMails.isNotEmpty()){
            mails.addAll(primaryMails)
            return
        }
        var id = 0L
        repeat(2){
            mails.add(Mail(++id,makeEnglishName(),"Primary$id","PrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimary","Primary"))
        }
        repeat(2){
            mails.add(Mail(++id,makeKoName(),"Primary$id","PrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimary","Primary"))
        }
        repeat(2){
            mails.add(Mail(++id,makeEnglishName(),"Primary$id","PrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimary","Primary"))
        }
        repeat(2){
            mails.add(Mail(++id,makeKoName(),"Primary$id","PrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimaryPrimary","Primary"))
        }
        primaryMails.addAll(mails)
    }

    private fun getSocialMails() {
        mails.clear()
        if (socialMails.isNotEmpty()){
            mails.addAll(socialMails)
            return
        }
        var id = 0L + 20L
        repeat(2){
            mails.add(Mail(++id,makeEnglishName(),"Social$id","SocialSocialSocialSocialSocialSocialSocialSocialSocialSocialSocial","Social"))
        }
        repeat(2){
            mails.add(Mail(++id,makeKoName(),"Social$id","SocialSocialSocialSocialSocialSocialSocialSocialSocialSocialSocial","Social"))
        }
        repeat(2){
            mails.add(Mail(++id,makeEnglishName(),"Social$id","SocialSocialSocialSocialSocialSocialSocialSocialSocialSocialSocial","Social"))
        }
        repeat(2){
            mails.add(Mail(++id,makeKoName(),"Social$id","SocialSocialSocialSocialSocialSocialSocialSocialSocialSocialSocial","Social"))
        }
        socialMails.addAll(mails)
    }

    private fun getPromotionMails() {
        mails.clear()
        if (promotionMails.isNotEmpty()){
            mails.addAll(promotionMails)
            return
        }
        var id = 0L + 40L
        repeat(2){
            mails.add(Mail(++id,makeEnglishName(),"Promotions$id","PromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotions","Promotions"))
        }
        repeat(2){
            mails.add(Mail(++id,makeKoName(),"Promotions$id","PromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotions","Promotions"))
        }
        repeat(2){
            mails.add(Mail(++id,makeEnglishName(),"Promotions$id","PromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotions","Promotions"))
        }
        repeat(2){
            mails.add(Mail(++id,makeKoName(),"Promotions$id","PromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotionsPromotions","Promotions"))
        }
        promotionMails.addAll(mails)
    }


    private fun makeEnglishName() : String {
        var name = "${('A'..'Z').random()}"
        repeat(5){
            name += ('a'..'z').random()
        }
        return name
    }
    private fun makeKoName() : String{
        var name = ""
        repeat(3){
            name += ('가'..'힣').random()
        }
        return name
    }
}