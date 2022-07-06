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
    private var emailCheck = false
    private var nicknameCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.nickname.observe(this){
            if (it.isEmpty()){
                binding.nicknameTextInputLayout.error = ""
                binding.nextButton.isEnabled = false
                nicknameCheck = false
            }else if(it.matches("^[a-zA-Z0-9]+\$".toRegex())&&it.length >= 4 && it.length <= 12){
                binding.nicknameTextInputLayout.error = ""
                nicknameCheck = true
                if (emailCheck) binding.nextButton.isEnabled= true
            }else{
                binding.nicknameTextInputLayout.error = "닉네임은 영문과 숫자를 포함한 4 ~ 12자로 입력하세요."
                binding.nextButton.isEnabled = false
                nicknameCheck = false
            }
        }
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
        binding.nextButton.setOnClickListener {
            startActivity(MainActivity.getInstance(this,viewModel.nickname.value!!,viewModel.email.value!!))
            finish()
        }
    }

}