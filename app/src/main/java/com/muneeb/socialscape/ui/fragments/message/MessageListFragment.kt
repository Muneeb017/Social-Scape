package com.muneeb.socialscape.ui.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.LikesAdapter
import com.muneeb.socialscape.adapters.MessageListAdapter
import com.muneeb.socialscape.databinding.FragmentMessageListBinding
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.model.local.ChatsListModel
import com.muneeb.socialscape.ui.fragments.search.SearchFragmentDirections

class MessageListFragment : Fragment(R.layout.fragment_message_list) {

    private val binding by viewBinding(FragmentMessageListBinding::bind)
    private val messageListAdapter by lazy { MessageListAdapter(list,this::onUserClick) }
    private val list: ArrayList<ChatsListModel> = ArrayList()

    private fun onUserClick(item: ChatsListModel) {
        val action = MessageListFragmentDirections.actionMessageFragmentToChatsFragment()
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvChatList.apply {
            setVerticalLayout()
            adapter = messageListAdapter
        }

        val chatsList = mutableListOf(
            ChatsListModel(
                "Muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!How are you doing?",
                "Just Now"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!How are you doing?",
                "1 hours ago"
            ), ChatsListModel(
                "Muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!",
                "40 min ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!",
                "2 days ago"
            ), ChatsListModel(
                "Muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!How are you doing?",
                "7 hours ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!How are you doing?",
                "4 days ago"
            ), ChatsListModel(
                "Muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!",
                "6 days ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "How are you doing?",
                "2 days ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!How are you doing?",
                "2 days ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!",
                "2 days ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "How are you doing?",
                "2 days ago"
            ), ChatsListModel(
                "Mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Hello,John!How are you doing?",
                "2 days ago"
            )
        )
        binding.rcvChatList.adapter = MessageListAdapter(chatsList, onClick = this::onUserClick)

    }


}