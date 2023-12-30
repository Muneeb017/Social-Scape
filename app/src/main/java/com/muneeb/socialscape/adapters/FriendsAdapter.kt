package com.muneeb.socialscape.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muneeb.socialscape.databinding.ItemFriendsListBinding
import com.muneeb.socialscape.databinding.ItemLikesBinding
import com.muneeb.socialscape.databinding.ItemSearchAccouBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.model.local.FriendsListModel
import com.muneeb.socialscape.model.local.LikesModel
import com.muneeb.socialscape.utils.loadImageFromUrl

class FriendsAdapter(
    private val list: MutableList<FriendsListModel>
) : RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFriendsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]

        with(holder.binding) {
            tvUserName.text = model.userName
            tvName.text = model.lastName
            ivPerson.loadImageFromUrl(model.image)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemFriendsListBinding) :
        RecyclerView.ViewHolder(binding.root)

}