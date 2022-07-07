package com.example.email.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.email.R
import com.example.email.activities.MainActivity
import com.example.email.adapters.MailAdapter
import com.example.email.databinding.FragmentSettingBinding
import com.example.email.viewmodels.SettingViewModel


class SettingFragment : Fragment() {

    private lateinit var binding : FragmentSettingBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[SettingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_setting,container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        viewModel.emailText.value = "Email : ${MainActivity.email}"
        viewModel.nicknameText.value = "NickName : ${MainActivity.nickName }"
    }

    companion object {
        fun newInstance() = SettingFragment()
    }
}