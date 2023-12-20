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
import com.muneeb.socialscape.utils.loadImageFromUrl

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

    }

    private fun setData() {
        FirestoreUtil.getUserData(onSuccess = { user ->
            if (user != null) {
                binding.tvName.text = user.name
                binding.tvUserName.text = user.userName
                binding.ivPerson.loadImageFromUrl(user.image)
            } else {
                Toast.makeText(requireContext(), "User dose not exist", Toast.LENGTH_SHORT).show()
            }
        }, onFailure = { e ->
            // Handle error
        })
    }

    override fun onResume() {
        super.onResume()

        setData()

    }
}