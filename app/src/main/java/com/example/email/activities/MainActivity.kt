package com.example.email.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.email.R
import com.example.email.databinding.ActivityMainBinding

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

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.lifecycleOwner = this

        initAppBar()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        /*if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //todo. 세로 모드 action
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            //todo. 가로 모드 action
        }*/
    }


    private fun initAppBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_30)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        return super.onOptionsItemSelected(item)
    }



}