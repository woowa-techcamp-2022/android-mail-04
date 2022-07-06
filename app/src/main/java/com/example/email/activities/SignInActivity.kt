package com.example.email.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    private var emailCheck = false
    private var nicknameCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        configurationCheck()
        nickNameObserve()
        emailObserve()

        binding.nextButton.setOnClickListener {
            startActivity(MainActivity.getInstance(this,viewModel.nickname.value!!,viewModel.email.value!!))
            finish()
        }
    }

    private fun configurationCheck(){
        val config = resources.configuration

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.topLinearlayout.visibility = View.VISIBLE
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.topLinearlayout.visibility = View.GONE
        }
    }

    private fun nickNameObserve(){
        viewModel.nickname.observe(this){
            if (it.isEmpty()){
                binding.nicknameTextInputLayout.error = ""
                binding.nextButton.isEnabled = false
                nicknameCheck = false
            }else if(it.matches("^[a-z][a-z\\d]{4,12}\$".toRegex())){
                binding.nicknameTextInputLayout.error = ""
                nicknameCheck = true
                if (emailCheck) binding.nextButton.isEnabled= true
            }else{
                binding.nicknameTextInputLayout.error = "닉네임은 영문과 숫자를 포함한 4 ~ 12자로 입력하세요."
                binding.nextButton.isEnabled = false
                nicknameCheck = false
            }
        }
    }

    private fun emailObserve(){
        viewModel.email.observe(this){
            val pattern = android.util.Patterns.EMAIL_ADDRESS
            if (it.isEmpty()){
                binding.emailTextInputLayout.error = ""
                binding.nextButton.isEnabled = false
                emailCheck = false
            }else if (pattern.matcher(it).matches()){
                binding.emailTextInputLayout.error = ""
                emailCheck = true
                if (nicknameCheck) binding.nextButton.isEnabled = true
            }else{
                binding.emailTextInputLayout.error = "이메일의 양식이 아닙니다. "
                binding.nextButton.isEnabled = false
                emailCheck = false
            }
        }
    }

}