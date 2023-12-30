package com.muneeb.socialscape.ui.fragments.likes_friend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.FriendsAdapter
import com.muneeb.socialscape.adapters.LikesAdapter
import com.muneeb.socialscape.databinding.FragmentFriendsBinding
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.local.FriendsListModel
import com.muneeb.socialscape.model.local.LikesModel

class FriendsFragment : Fragment(R.layout.fragment_friends) {

    private val binding by viewBinding(FragmentFriendsBinding::bind)
    private val friendsAdapter by lazy { FriendsAdapter(list) }
    private val list: ArrayList<FriendsListModel> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rcvFriends.apply {
            setVerticalLayout()
            adapter = friendsAdapter
        }
        val friendsList = mutableListOf(
            FriendsListModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Designer"
            ), FriendsListModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Designer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Designer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Designer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "software engineer"
            ), FriendsListModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Designer"
            )
        )
        binding.rcvFriends.adapter = FriendsAdapter(friendsList)

    }

}