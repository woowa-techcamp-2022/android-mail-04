package com.example.email.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.email.R
import com.example.email.data.Mail
import com.example.email.databinding.MailItemBinding

/**
 * recyclerView 와 Mail 목록을 연결해줄 Adapter
 */
class MailAdapter : RecyclerView.Adapter<MailAdapter.MailItemViewHolder>() {

    /**
     * ViewHolder, View 에 데이터를 입력?한다.
     */
    class MailItemViewHolder(
        private val binding : MailItemBinding
    ) : RecyclerView.ViewHolder(binding.root){

        /**
         * 적당한 색 모음
         */
        private val colorList = intArrayOf(
            0xffef9a9a.toInt(), 0xffffe082.toInt(), 0xffc5e1a5.toInt(),
            0xff80cbc4.toInt(), 0xffb39ddb.toInt(), 0xff9fa8da.toInt(),
            0xffffcc80.toInt(), 0xffce93d8.toInt()
        )

        /**
         * view 에 mail 데이터를 입력한다.
         * 프로필 이미지의 경우
         * sender 의 이름 첫 글자가 알파벳일 경우 첫 글자를 표시한다.
         * sender 의 이름 첫 글자가 한글일 경우 기본 프로필 아이콘을 표시한다.
         * colorList 의 색 중 랜덤으로 하나를 뽑아 색을 덧씌운다.
         */
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

    /**
     * 이전 목록과 새로 업데이트된 목록을 비교하여 알맞은 Notify 함수를 실행시키기 위한 DiffUtil.Callback
     */
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

    /**
     * recyclerView 에 실질적으로 출력될 메일 목록
     */
    private val mailList = mutableListOf<Mail>()

    /**
     * 메일 목록 업데이트 함수 DiffUtilCallBack 을 이용해
     * 목록의 변화를 확인하고 View 를 업데이트한다.
     */
    fun updateList(mails : List<Mail>){
        val diffUtilCallBack = DiffUtilCallBack(mailList,mails)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        mailList.run {
            clear()
            addAll(mails)
            diffResult.dispatchUpdatesTo(this@MailAdapter)
        }
    }

    /**
     * viewHolder 생성 함수
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mail_item,parent,false)
        val binding = MailItemBinding.bind(view)
        return MailItemViewHolder(binding)
    }

    /**
     * viewHolder 의 bind 를 호출해 메일 목록의 메일을 View에 입력
     */
    override fun onBindViewHolder(holder: MailItemViewHolder, position: Int) {
        holder.bind(mailList[position])
    }

    /**
     * 리스트의 아이템 개수를 반환
     */
    override fun getItemCount(): Int  = mailList.size
}