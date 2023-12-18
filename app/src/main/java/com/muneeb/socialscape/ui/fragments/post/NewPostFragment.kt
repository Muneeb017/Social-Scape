package com.muneeb.socialscape.ui.fragments.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentNewPostBinding
import com.muneeb.socialscape.databinding.FragmentProfileBinding

class NewPostFragment : Fragment(R.layout.fragment_new_post) {

    private val binding by viewBinding(FragmentNewPostBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}