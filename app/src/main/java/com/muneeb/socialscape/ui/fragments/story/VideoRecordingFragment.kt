package com.muneeb.socialscape.ui.fragments.story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentStoryBinding
import com.muneeb.socialscape.databinding.FragmentVideoRecordingBinding

class VideoRecordingFragment : Fragment(R.layout.fragment_video_recording) {

    private val binding by viewBinding(FragmentVideoRecordingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}