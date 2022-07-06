package com.example.email.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.email.R
import com.example.email.adapters.MailAdapter
import com.example.email.databinding.ActivityMainBinding
import com.example.email.viewmodels.MainViewModel
import com.google.android.material.navigation.NavigationView

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

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.sideNavView.visibility = View.GONE
            binding.bottomNav.visibility = View.VISIBLE
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.sideNavView.visibility = View.VISIBLE
            binding.bottomNav.visibility = View.GONE
        }

        initAppBar()
        initRecyclerView()
    }

    private fun initAppBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_30)

        binding.drawerNavView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item1 -> {
                    viewModel.drawerNavPosition = 0
                    viewModel.typeText.postValue("Primary")
                    viewModel.getPrimaryMails()
                }
                R.id.item2 ->{
                    viewModel.drawerNavPosition = 1
                    viewModel.typeText.postValue("Social")
                    viewModel.getSocialMails()
                }
                R.id.item3 -> {
                    viewModel.drawerNavPosition = 2
                    viewModel.typeText.postValue("Promotion")
                    viewModel.getPromotionMails()
                }
                else -> {}
            }
            mailAdapter.updateList(viewModel.mails)
            return@setNavigationItemSelectedListener false
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