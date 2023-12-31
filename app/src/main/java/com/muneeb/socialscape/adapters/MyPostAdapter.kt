package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemPostBinding
import com.muneeb.socialscape.databinding.ItemUserPostBinding
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.utils.loadImageFromUrl

class MyPostAdapter(
    private val list: ArrayList<Post>,val onClick: (Post) -> Unit
) : RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            ivPost.loadImageFromUrl(model.image)

//            // Set a long click listener
//            root.setOnLongClickListener {
//                onClick(model)
//                true
//            }
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemUserPostBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnPostItemLongClickListener {
        fun onPostItemLongClick(post: Post)
    }

    fun refresh(l: ArrayList<Post>) {
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }
}