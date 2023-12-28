package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemPostBinding
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.utils.loadImageFromUrl

class NewPostAdapter(
    private val list: ArrayList<Post>, val onClick: (Post) -> Unit
) : RecyclerView.Adapter<NewPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            tvUserName.text = model.name
            tvName.text = model.text
            ivPost.loadImageFromUrl(model.image)

//            llLikes.setOnClickListener {
//                onClick(model)
//            }
        }

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    fun refresh() = notifyDataSetChanged()

}