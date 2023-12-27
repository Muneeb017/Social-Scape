package com.muneeb.socialscape.ui.fragments.post

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flashbid.luv.extensions.viewBinding
import com.google.firebase.storage.FirebaseStorage
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentNewPostBinding
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.loadImageFromUrl
import com.muneeb.socialscape.utils.setArrayAdapter

class NewPostFragment : Fragment(R.layout.fragment_new_post) {

    private val binding by viewBinding(FragmentNewPostBinding::bind)
    private val args by navArgs<NewPostFragmentArgs>()
    private var gender = ""
    private lateinit var postUrl: String
    private var storageRef = FirebaseStorage.getInstance()

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
            uploadPicture(args.uri)
            findNavController().navigate(R.id.action_newPostFragment_to_homeFragment)
        }

        binding.ivPost.setImageURI(Uri.parse(args.uri))

    }

    private fun uploadPicture(uri: String) {
        storageRef.getReference("images").child(System.currentTimeMillis().toString())
            .putFile(Uri.parse(uri)).addOnSuccessListener { task ->
                task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                    postUrl = url.toString()
                    createUserPost()
                }
            }
    }

    private fun setData() {
        FirestoreUtil.getUserData(onSuccess = { user ->
            if (user != null) {
                binding.tvName.setText(user.name)
                binding.ivPerson.loadImageFromUrl(user.image)
            } else {
                Toast.makeText(requireContext(), "User dose not exist", Toast.LENGTH_SHORT).show()
            }
        }, onFailure = { e ->
            // Handle error
        })
    }

    private fun createUserPost() {
        FirestoreUtil.createPost(text = binding.edtPost.text.toString(),
            privacy = binding.btnPrivate.text.toString(),
            name = binding.tvName.text.toString(),
            image = postUrl,
            onSuccess = {
                Toast.makeText(requireContext(),"Uploaded Picture",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_newPostFragment_to_homeFragment)
            }) { e ->
            // Handle error
        }
    }

    private fun setGenderList() {
        binding.btnPrivate.setArrayAdapter(genderList)
        binding.btnPrivate.setOnItemClickListener { _, _, position, _ ->
            gender = genderList[position]
        }
    }

    override fun onResume() {
        super.onResume()

        setData()

    }

}