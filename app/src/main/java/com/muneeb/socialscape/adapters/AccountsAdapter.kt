package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemSearchAccouBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.loadImageFromUrl

class AccountsAdapter(
    val onClick: (OtherUser) -> Unit
    ) : RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    private val list: ArrayList<OtherUser> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchAccouBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            tvName.text = model.name
            tvUserName.text = "@" + model.userName
            ivPerson.loadImageFromUrl(model.image)

        }
        holder.itemView.setOnClickWithDebounce {
            onClick(model)
        }

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemSearchAccouBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun refresh(l: ArrayList<OtherUser>) {
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }
}