package com.example.email.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.email.R
import com.example.email.databinding.ActivitySignInBinding
import com.example.email.navigator.SignInNavigator
import com.example.email.viewmodels.SignInViewModel
import java.lang.ref.WeakReference

class SignInActivity : AppCompatActivity(), SignInNavigator {

    /**
     * binding, 호출 시 초기화
     */
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
    }

    /**
     * viewModel, 호출 시 초기화
     * viewModel 가 activity 객체를 참조를 할 수 있도록 등록
     */
    private val viewModel by lazy {
        ViewModelProvider(this)[SignInViewModel::class.java].also {
            it.navigatorRef = WeakReference(this)
        }
    }

    /**
     * 이메일과 닉네임이 둘 다 규칙에 맞는지 확인하기 위한 Boolean 값
     */
    private var emailCheck = false
    private var nicknameCheck = false

    /**
     * onCreate(),
     * 화면 정보 확인
     * nickName Observe
     * email Observe
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        configurationCheck()
        nickNameObserve()
        emailObserve()
    }

    /**
     * 가로 세로 모드를 확인해 가로 모드일 경우 topLinearlayout 을 지운다.
     */
    private fun configurationCheck(){
        val config = resources.configuration

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.topLinearlayout.visibility = View.VISIBLE
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.topLinearlayout.visibility = View.GONE
        }
    }

    /**
     * 닉네임 관찰,
     * 입력 칸의 닉네임이 규칙에 부합하는지 관찰한다.
     * 닉네임의 규칙
     * 1. 닉네임은 알파벳과 숫자를 결합한 4 ~ 12 자리의 문자열이다.
     *
     * - 닉네임이 비어있다면 error Text 는 없지만 버튼은 비활성화 된다. 당연히 규칙에 부합하지 않는다.
     * - 닉네임이 규칙에 부합한다면 error Text 가 사라지고 nicknameCheck 를 true 로 변경,
     *   이메일도 확인이 완료된 상태라면 버튼 활성화
     * - 닉네임이 규칙에 부합하지 않는다면 error Text 출력, 버튼 비활성화, nicknameCheck 를 false 로 변경
     */
    private fun nickNameObserve(){
        viewModel.nickname.observe(this){
            if (it.isEmpty()){
                binding.nicknameTextInputLayout.error = ""
                binding.nextButton.isEnabled = false
                nicknameCheck = false
            }else if(nickNameChecker(it)){
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

    /**
     * 닉네임 규칙 확인 함수
     * 알파벳과 숫자를 결합한 4 ~ 12 자리의 문자열인지 확인한다.
     */
    private fun nickNameChecker(nickName: String) : Boolean{
        var alphabet = 0
        var num = 0
        if (nickName.length in 4..12){
            for(c in nickName){
                when (c) {
                    in 'a'..'z', in 'A'..'Z' -> alphabet++
                    in '0'..'9' -> num++
                    else -> return false
                }
            }
            return !(num == 0 || alphabet ==0)
        }
        return false
    }

    /**
     * 이메일 관찰,
     * 입력 칸의 이메일 규칙에 부합하는지 관찰한다.
     * 이메일의 규칙은 android.util.Patterns.EMAIL_ADDRESS 를 따른다.
     *
     * - 이메일이 비어있다면 error Text 는 없지만 버튼은 비활성화 된다. 당연히 규칙에 부합하지 않는다.
     * - 이메일이 규칙에 부합한다면 error Text 가 사라지고 emailCheck 를 true 로 변경,
     *   닉네임도 확인이 완료된 상태라면 버튼 활성화
     * - 이메일이 규칙에 부합하지 않는다면 error Text 출력, 버튼 비활성화, emailCheck 를 false 로 변경
     */
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
                binding.emailTextInputLayout.error = "이메일의 양식이 아닙니다."
                binding.nextButton.isEnabled = false
                emailCheck = false
            }
        }
    }

    /**
     * viewModel 에서 WeakReference 로 참조해 사용할 함수.
     * MainActivity 실행, 닉네임과 이메일을 MainActivity 로 넘겨준다.
     */
    override fun startMainActivity(nickName : String, email : String) {
        startActivity(MainActivity.getInstance(this,nickName,email))
        finish()
    }

}