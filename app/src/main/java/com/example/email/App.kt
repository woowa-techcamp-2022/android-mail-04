package com.example.email

import android.app.Application
import androidx.lifecycle.MutableLiveData

class App : Application() {
    companion object{
        /**
         * 표시되고 있는 메일의 타입 정보
         * 0 -> Primary
         * 1 -> Social
         * 2 -> Promotions
         *
         * activity 에서 DrawerNavigationView 를 통해 타입 정보를 입력받고
         * 해당 값을 변경 -> fragment 에서 해당 값을 관찰하여 recyclerView 갱신
         */
        val mailType  = MutableLiveData(0)
    }
}