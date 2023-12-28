package com.muneeb.socialscape.ui.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.FriendsAdapter
import com.muneeb.socialscape.adapters.MessageListAdapter
import com.muneeb.socialscape.databinding.FragmentMessageBinding
import com.muneeb.socialscape.extensions.setVerticalLayout

class MessageListFragment : Fragment(R.layout.fragment_message) {

    private val binding by viewBinding(FragmentMessageBinding::bind)
    private val messageListAdapter by lazy { MessageListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvChatList.apply {
            setVerticalLayout()
            adapter = messageListAdapter
        }

    }
}