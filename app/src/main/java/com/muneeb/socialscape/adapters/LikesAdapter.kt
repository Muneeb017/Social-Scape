package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemLikesBinding
import com.muneeb.socialscape.model.LikesModel
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.loadImageFromUrl

class LikesAdapter(private val list: MutableList<LikesModel>) :
    RecyclerView.Adapter<LikesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLikesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            tvUserName.text = model.name
            tvComment.text = model.text2
            tvTime.text = model.text
            ivPerson.loadImageFromUrl(model.image)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemLikesBinding) : RecyclerView.ViewHolder(binding.root)

}