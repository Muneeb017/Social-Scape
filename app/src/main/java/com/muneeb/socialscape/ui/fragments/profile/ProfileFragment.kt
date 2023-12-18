package com.muneeb.socialscape.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentProfileBinding
import com.muneeb.socialscape.databinding.FragmentSearchBinding
import com.muneeb.socialscape.utils.FirestoreUtil

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSetting.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        setData()
    }

    private fun setData() {
        FirestoreUtil.getUserData(onSuccess = { user ->
            if (user != null) {
                // User data retrieved successfully
                // Access user properties like user.name, user.email, etc.
                binding.tvName.text = user.name
                binding.tvUserName.text = user.userName
            } else {
                // Current user document does not exist
                Toast.makeText(requireContext(), "User dose not exist", Toast.LENGTH_SHORT).show()
            }
        }, onFailure = { e ->
            // Handle error
        })
    }
}