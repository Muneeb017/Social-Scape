package com.muneeb.socialscape.ui.fragments.likes_friend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentLikesBinding

class LikesFragment : Fragment(R.layout.fragment_activity_likes) {

    private val binding by viewBinding(FragmentLikesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}