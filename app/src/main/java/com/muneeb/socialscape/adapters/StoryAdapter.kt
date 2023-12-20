package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemPersonBinding
import com.muneeb.socialscape.model.StoryModel
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.loadImageFromUrl

class StoryAdapter(private val list: MutableList<StoryModel>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            tvPerson.text = model.name
            ivPerson.loadImageFromUrl(model.image)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

//    fun refresh(newList: ArrayList<ArrayList<StoryModel>>) {
//        list.clear()
//        list.addAll(newList)
//        notifyDataSetChanged()
//    }

}