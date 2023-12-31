package com.muneeb.socialscape.ui.fragments.call

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentCallsBinding
import com.muneeb.socialscape.databinding.FragmentVideoCallBinding

class VideoCallFragment : Fragment(R.layout.fragment_video_call) {

    private val binding by viewBinding(FragmentVideoCallBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}