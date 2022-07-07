package com.example.email.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.email.R
import com.example.email.data.Mail
import com.example.email.databinding.MailItemBinding

class MailAdapter : RecyclerView.Adapter<MailAdapter.MailItemViewHolder>() {

    class MailItemViewHolder(
        private val binding : MailItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        private val colorList = intArrayOf(
            0xffef9a9a.toInt(), 0xffffe082.toInt(), 0xffc5e1a5.toInt(),
            0xff80cbc4.toInt(), 0xffb39ddb.toInt(), 0xff9fa8da.toInt(),
            0xffffcc80.toInt(), 0xffce93d8.toInt()
        )

        fun bind(item : Mail){
            binding.mail = item
            val initial = item.sender.toCharArray()[0]
            if (initial in 'a'..'z' || initial in 'A'..'Z') {
                binding.profileImg.text = "${item.sender.toCharArray()[0]}"
                binding.profileImg.setBackgroundResource(R.drawable.circle_shape)
            }else if(initial in '가'..'힣'){
                binding.profileImg.text = ""
                binding.profileImg.setBackgroundResource(R.drawable.ic_baseline_account_circle_24)
            }
            val random = colorList.random()
            binding.profileImg.backgroundTintList = ColorStateList.valueOf(random)
        }
    }

    class DiffUtilCallBack(
        private val oldList : List<Mail>,
        private val newList : List<Mail>
    ):DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return  oldList[oldItemPosition].id == newList[newItemPosition].id
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return  oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    private val mailList = mutableListOf<Mail>()

    fun updateList(mails : List<Mail>){
        /*mailList.clear()
        mailList.addAll(mails)
        notifyDataSetChanged()*/
        val diffUtilCallBack = DiffUtilCallBack(mailList,mails)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        mailList.run {
            clear()
            addAll(mails)
            diffResult.dispatchUpdatesTo(this@MailAdapter)
        }
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