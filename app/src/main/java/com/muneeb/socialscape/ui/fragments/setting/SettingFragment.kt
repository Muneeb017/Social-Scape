package com.muneeb.socialscape.ui.fragments.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentSettingBinding
import com.muneeb.socialscape.databinding.FragmentVideoCallBinding

class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val binding by viewBinding(FragmentSettingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_editProfileFragment)
        }

    }

}