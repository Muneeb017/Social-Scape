package com.muneeb.socialscape.ui.fragments.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentNewPostBinding
import com.muneeb.socialscape.databinding.FragmentProfileBinding
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.setArrayAdapter

class NewPostFragment : Fragment(R.layout.fragment_new_post) {

    private val binding by viewBinding(FragmentNewPostBinding::bind)
    private var gender = ""

    private val genderList by lazy {
        arrayListOf<String>().apply {
            add(getString(R.string.btn_private))
            add(getString(R.string.btn_public))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenderList()
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnPost.setOnClickListener {
            FirestoreUtil.createPost(
                text = binding.edtPost.text.toString(),
                privacy = binding.btnPrivate.text.toString(),
                name = binding.tvUserName.text.toString(),
                onSuccess = {
                    findNavController().navigate(R.id.action_newPostFragment_to_homeFragment)
                },
                onFailure = { e ->
                    // Handle error
                })
        }

    }

    private fun setGenderList() {
        binding.btnPrivate.setArrayAdapter(genderList)
        binding.btnPrivate.setOnItemClickListener { _, _, position, _ ->
            gender = genderList[position]
        }
    }
}