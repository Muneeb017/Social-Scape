package com.muneeb.socialscape.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentEditProfileBinding
import com.muneeb.socialscape.databinding.FragmentProfileBinding

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val binding by viewBinding(FragmentEditProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}