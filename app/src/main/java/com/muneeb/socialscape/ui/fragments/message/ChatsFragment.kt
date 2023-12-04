package com.muneeb.socialscape.ui.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentChatsBinding
import com.muneeb.socialscape.databinding.FragmentCommentsBinding

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    private val binding by viewBinding(FragmentChatsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}