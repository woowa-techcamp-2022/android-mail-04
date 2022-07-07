package com.example.email.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.email.R
import com.example.email.activities.MainActivity
import com.example.email.databinding.FragmentSettingBinding
import com.example.email.viewmodels.SettingViewModel

class SettingFragment : Fragment() {

    /**
     * viewModel, 호출 시 초기화
     */
    private val viewModel by lazy {
        ViewModelProvider(this)[SettingViewModel::class.java]
    }
    /**
     * binding, onCreateView 에서 초기화
     */
    private lateinit var binding : FragmentSettingBinding

    /**
     * binding 초기화
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
        return binding.root
    }

    /**
     * 싱글톤으로 저장되어 있는 이메일과 닉네임 정보를 viewModel 에 입력
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.emailText.value = "Email : ${MainActivity.email}"
        viewModel.nicknameText.value = "NickName : ${MainActivity.nickName }"
    }

}