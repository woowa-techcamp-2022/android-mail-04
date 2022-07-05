package com.example.email.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.email.R
import com.example.email.databinding.ActivitySignInBinding
import com.example.email.viewmodels.SignInViewModel

class SignInActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[SignInViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}