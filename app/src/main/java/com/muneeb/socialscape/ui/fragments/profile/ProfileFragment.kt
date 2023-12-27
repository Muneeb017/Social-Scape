package com.muneeb.socialscape.ui.fragments.profile

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.AccountsAdapter
import com.muneeb.socialscape.adapters.MyPostAdapter
import com.muneeb.socialscape.databinding.DailogDeleteBinding
import com.muneeb.socialscape.databinding.FragmentProfileBinding
import com.muneeb.socialscape.databinding.FragmentSearchBinding
import com.muneeb.socialscape.extensions.setGridLayout
import com.muneeb.socialscape.extensions.setOnClickWithDebounce
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.loadImageFromUrl

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val myPostAdapter = MyPostAdapter(this::onItemClick)


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

        binding.rcvSearchDiscover.apply {
            setGridLayout(2)
            adapter = myPostAdapter
        }

        FirestoreUtil.getUploadedPosts(onSuccess = { postsList ->
            myPostAdapter.refresh(postsList.toCollection(ArrayList()))
        }, onFailure = { e ->
            // Handle error
        })

    }

    private fun setData() {
        FirestoreUtil.getUserData(onSuccess = { user ->
            if (user != null) {
                binding.tvName.text = user.name
                binding.tvUserName.text = user.userName
                binding.ivPerson.loadImageFromUrl(user.image)
                binding.tvCountFollowing.setText((user.followings?.size ?: 0).toString())
                binding.tvCountFollowers.setText((user.followers?.size ?: 0).toString())
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