package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.R

class MainViewModel(app : Application) : AndroidViewModel(app) {
    /**
     * BottomNavigationView, NavigationRailView 의 선택 아이템 위치 정보
     */
    var navPosition = MutableLiveData(R.id.item_mail)
}