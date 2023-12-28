package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemLikesBinding
import com.muneeb.socialscape.databinding.ItemSearchAccouBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.loadImageFromUrl

class CommentsAdapter() : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private val list: ArrayList<OtherUser> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLikesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {

        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemLikesBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun refresh(l: ArrayList<OtherUser>) {
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }
}