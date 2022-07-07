package com.example.email.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.email.R

class MainViewModel(app : Application) : AndroidViewModel(app) {
    var navPosition = MutableLiveData(R.id.item_mail) // side, bottom nav position
}