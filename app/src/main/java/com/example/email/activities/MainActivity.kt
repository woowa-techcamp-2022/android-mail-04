package com.example.email.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.email.R

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var nickName: String
        lateinit var email : String
        fun getInstance(context : Context, nickName : String, email : String) : Intent {
            this.nickName = nickName
            this.email = email
            return Intent(context,MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}