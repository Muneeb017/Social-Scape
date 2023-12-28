package com.muneeb.socialscape.ui.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.FriendsAdapter
import com.muneeb.socialscape.databinding.FragmentCommentsBinding
import com.muneeb.socialscape.databinding.FragmentMessageBinding
import com.muneeb.socialscape.extensions.setVerticalLayout

class CommentsFragment : Fragment(R.layout.fragment_comments) {

    private val binding by viewBinding(FragmentCommentsBinding::bind)
    private val friendsAdapter by lazy { FriendsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtComment.background = null

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rcvComments.apply {
            setVerticalLayout()
            adapter = friendsAdapter
        }

    }
}