package com.example.email.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.email.R
import com.example.email.data.Mail
import com.example.email.databinding.MailItemBinding

class MailAdapter : RecyclerView.Adapter<MailAdapter.MailItemViewHolder>() {

    private val mailList = mutableListOf<Mail>()

    class MailItemViewHolder(
        private val binding : MailItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Mail){
            binding.mail = item
        }
    }

    fun updateList(mails : List<Mail>){
        mailList.clear()
        mailList.addAll(mails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mail_item,parent,false)
        val binding = MailItemBinding.bind(view)
        return MailItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MailItemViewHolder, position: Int) {
        holder.bind(mailList[position])
    }

    override fun getItemCount(): Int  = mailList.size
}