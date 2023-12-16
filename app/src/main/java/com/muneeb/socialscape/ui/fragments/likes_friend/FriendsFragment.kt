package com.muneeb.socialscape.ui.fragments.likes_friend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentFriendsBinding

class FriendsFragment : Fragment(R.layout.fragment_friends) {

    private val binding by viewBinding(FragmentFriendsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}