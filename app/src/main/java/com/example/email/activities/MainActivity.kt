package com.example.email.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.email.App
import com.example.email.R
import com.example.email.databinding.ActivityMainBinding
import com.example.email.fragments.MailFragment
import com.example.email.fragments.SettingFragment
import com.example.email.viewmodels.MainViewModel
import com.google.android.material.navigation.NavigationBarView

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

    inner class NavItemSelectedListener : NavigationBarView.OnItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val ft = supportFragmentManager.beginTransaction()
            val frameId = binding.fragmentFrame.id
            when(item.itemId){
                R.id.item_mail -> {
                    if (viewModel.navPosition.value!! != item.itemId)
                        viewModel.navPosition.postValue(R.id.item_mail)
                    ft.replace(frameId, mailFrag)
                }
                R.id.item_setting -> {
                    if (viewModel.navPosition.value!! != item.itemId)
                        viewModel.navPosition.postValue(R.id.item_mail)
                    viewModel.navPosition.postValue(R.id.item_setting)
                    ft.replace(frameId,settingFrag)
                }
            }
            ft.commit()
            return true
        }
    }

    private val settingFrag  = SettingFragment.newInstance()
    private val mailFrag  = MailFragment.newInstance()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        configurationCheck()
        initAppBar()
        initNavi()
    }

    private fun configurationCheck(){
        val config = resources.configuration
        val density = DisplayMetrics().density
        val width = DisplayMetrics().widthPixels * density

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.sideNav.visibility = View.GONE
            binding.bottomNav.visibility = View.VISIBLE
        } else if (width > 600 ||config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.sideNav.visibility = View.VISIBLE
            binding.bottomNav.visibility = View.GONE
        }
    }

    private fun initNavi() {
        binding.bottomNav.setOnItemSelectedListener(NavItemSelectedListener())
        binding.sideNav.setOnItemSelectedListener(NavItemSelectedListener())
        viewModel.navPosition.observe(this){
            binding.bottomNav.selectedItemId = it
            binding.sideNav.selectedItemId = it
        }
    }

    private fun initAppBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_30)

        binding.drawerNavView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item_primary -> App.mailType.postValue(0)
                R.id.item_social -> App.mailType.postValue(1)
                R.id.item_promotions -> App.mailType.postValue(2)
            }
            viewModel.navPosition.postValue(R.id.item_mail)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (App.mailType.value!! == 0 && viewModel.navPosition.value == R.id.item_mail){
            finish()
        }else {
            viewModel.navPosition.postValue(R.id.item_mail)
            if (App.mailType.value!! != 0){
                App.mailType.value = 0
                binding.drawerNavView.setCheckedItem(R.id.item_primary)
            }
        }
    }
}