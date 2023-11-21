package com.muneeb.socialscape.ui.fragments.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentHomeBinding
import com.muneeb.socialscape.databinding.FragmentMessageBinding

class MessageFragment : Fragment(R.layout.fragment_message) {

    private val binding by viewBinding(FragmentMessageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}