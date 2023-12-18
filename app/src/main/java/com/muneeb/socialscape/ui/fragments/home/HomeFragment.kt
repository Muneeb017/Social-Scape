package com.muneeb.socialscape.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivNewPost.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newPostFragment)
        }
        binding.ivNotification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_likesFragment)
        }

    }
}