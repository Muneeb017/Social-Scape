package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemChatBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.model.local.ChatsListModel
import com.muneeb.socialscape.utils.loadImageFromUrl

class MessageListAdapter(
     private val list: MutableList<ChatsListModel>,
     val onClick: (ChatsListModel) -> Unit
) : RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            tvUserName.text = model.userName
            tvChat.text = model.chat
            tvTime.text = model.time
            ivPerson.loadImageFromUrl(model.image)
        }
        holder.itemView.setOnClickWithDebounce {
            onClick(model)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root)

}