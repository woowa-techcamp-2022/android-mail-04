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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.email.R
import com.example.email.adapters.MailAdapter
import com.example.email.databinding.ActivityMainBinding
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
                    viewModel.navPosition.postValue(R.id.item_mail)
                    binding.typeTextView.visibility = View.VISIBLE
                    binding.mailRecyclerView.visibility = View.VISIBLE
                    ft.remove(settingFrag)
                }
                R.id.item_setting -> {
                    viewModel.navPosition.postValue(R.id.item_setting)
                    binding.typeTextView.visibility = View.GONE
                    binding.mailRecyclerView.visibility = View.GONE
                    ft.replace(frameId,settingFrag)
                }
            }
            ft.commit()
            return true
        }
    }

    private val settingFrag = SettingFragment.newInstance()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val mailAdapter = MailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

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

        initAppBar()
        initRecyclerView()
        initNavi()
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
                R.id.item_primary -> {
                    viewModel.drawerNavPosition = 0
                    viewModel.typeText.postValue("Primary")
                    viewModel.getPrimaryMails()
                }
                R.id.item_social ->{
                    viewModel.drawerNavPosition = 1
                    viewModel.typeText.postValue("Social")
                    viewModel.getSocialMails()
                }
                R.id.item_promotions -> {
                    viewModel.drawerNavPosition = 2
                    viewModel.typeText.postValue("Promotion")
                    viewModel.getPromotionMails()
                }
                else -> {}
            }
            mailAdapter.updateList(viewModel.mails)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun initRecyclerView(){
        binding.mailRecyclerView.adapter = mailAdapter
        binding.mailRecyclerView.layoutManager = LinearLayoutManager(this)
        when(viewModel.drawerNavPosition){
            0 -> viewModel.getPrimaryMails()
            1 -> viewModel.getSocialMails()
            2 -> viewModel.getPromotionMails()
        }
        mailAdapter.updateList(viewModel.mails)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}