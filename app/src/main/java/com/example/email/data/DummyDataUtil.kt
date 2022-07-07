package com.example.email.data

object DummyDataUtil {
    private val primaryMails = mutableListOf<Mail>()
    private val socialMails = mutableListOf<Mail>()
    private val promotionMails = mutableListOf<Mail>()

    fun getMails(type : Int) : List<Mail>
    = when(type){
            0 -> getPrimaryMails()
            1 -> getSocialMails()
            2 -> getPromotionMails()
            else -> mutableListOf()
        }

    private fun getPrimaryMails() : List<Mail>{
        if (primaryMails.isNotEmpty()) return primaryMails
        val mails = mutableListOf<Mail>()
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
        return mails
    }

    private fun getSocialMails() : List<Mail>{
        if (socialMails.isNotEmpty()) return socialMails
        val mails = mutableListOf<Mail>()
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
        return mails
    }

    private fun getPromotionMails() : List<Mail>{
        if (promotionMails.isNotEmpty()) return promotionMails
        val mails = mutableListOf<Mail>()
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
        return mails
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