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

    private val viewModel by lazy {
        ViewModelProvider(this)[MailViewModel::class.java]
    }
    private lateinit var binding : FragmentMailBinding

    private val mailAdapter = MailAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.mailRecyclerView.adapter = mailAdapter
        binding.mailRecyclerView.layoutManager = LinearLayoutManager(context)
        App.mailType.observe(viewLifecycleOwner){
            viewModel.getMails()
            mailAdapter.updateList(viewModel.mails)
        }
    }

    companion object {
        fun newInstance() = MailFragment()
    }
}