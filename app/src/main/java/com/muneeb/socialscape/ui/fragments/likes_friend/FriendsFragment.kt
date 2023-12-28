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

class FriendsFragment : Fragment(R.layout.fragment_friends) {

    private val binding by viewBinding(FragmentFriendsBinding::bind)
    private val friendsAdapter by lazy { FriendsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rcvFriends.apply {
            setVerticalLayout()
            adapter = friendsAdapter
        }

    }

}