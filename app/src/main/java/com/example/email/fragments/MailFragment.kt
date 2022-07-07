package com.example.email.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.email.App
import com.example.email.R
import com.example.email.adapters.MailAdapter
import com.example.email.databinding.FragmentMailBinding
import com.example.email.viewmodels.MailViewModel

class MailFragment : Fragment() {

    /**
     * viewModel, 호출 시 초기화
     */
    private val viewModel by lazy {
        ViewModelProvider(this)[MailViewModel::class.java]
    }

    /**
     * binding, onCreateView 에서 초기화
     */
    private lateinit var binding : FragmentMailBinding

    /**
     *  recyclerView 에 등록할 MailAdapter
     */
    private val mailAdapter = MailAdapter()

    /**
     * onCreateView()
     * binding 초기화
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mail,container,false)
        return binding.root
    }

    /**
     * onViewCreated()
     * View 초기화
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        initRecyclerView()
    }

    /**
     * recyclerView 초기화 ( adapter 등록, layoutManager 등록)
     * App 의 mailType 을 관찰해 변화가 있을 시 recyclerView 갱신
     */
    private fun initRecyclerView(){
        binding.mailRecyclerView.adapter = mailAdapter
        binding.mailRecyclerView.layoutManager = LinearLayoutManager(context)
        App.mailType.observe(viewLifecycleOwner){
            viewModel.getMails()
            mailAdapter.updateList(viewModel.mails)
        }
    }

    /**
     * instance 생성
     */
    companion object {
        fun newInstance() = MailFragment()
    }
}