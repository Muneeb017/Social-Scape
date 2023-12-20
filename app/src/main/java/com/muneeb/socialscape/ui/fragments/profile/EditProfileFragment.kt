package com.muneeb.socialscape.ui.fragments.profile

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.muneeb.socialscape.R
import com.muneeb.socialscape.data.local.AppPreferences
import com.muneeb.socialscape.databinding.FragmentEditProfileBinding
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.loadImageFromUrl
import com.muneeb.socialscape.utils.setArrayAdapter

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val binding by viewBinding(FragmentEditProfileBinding::bind)
    private val pref by lazy { AppPreferences(requireContext()) }
    private var gender = ""

    private var storageRef = Firebase.storage
    private lateinit var uri: Uri

    private val genderList by lazy {
        arrayListOf<String>().apply {
            add(getString(R.string.male))
            add(getString(R.string.female))
            add(getString(R.string.other))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storageRef = FirebaseStorage.getInstance()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSave.setOnClickListener {
            addUserUpdateDataToDB()
        }
        setGenderList()

        val galleryImage =
            registerForActivityResult(ActivityResultContracts.GetContent(), ActivityResultCallback {
                if (it != null) {
                    uri = it
                }
            })

        binding.btnPickNew.setOnClickListener {
            galleryImage.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri).addOnSuccessListener { task ->
                    task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                        updateProfileImage(url.toString())
                    }
                }
        }

    }

    private fun updateProfileImage(url: String) {
        FirestoreUtil.updateProfileImage(
            imageUrl = url,
            onSuccess = {
                binding.ivImage.loadImageFromUrl(url)
                pref.photo = url
                Toast.makeText(
                    requireContext(), "Image Upload Successful", Toast.LENGTH_SHORT
                ).show()
            },
            onFailure = { e ->
                Toast.makeText(
                    requireContext(), "Image Upload Failed ${e.localizedMessage}", Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    private fun addUserUpdateDataToDB() {
        FirestoreUtil.createOrUpdateUserData(User(
            name = binding.edtName.text.toString(),
            userName = binding.edtUserName.text.toString(),
            age = binding.edtAge.text.toString(),
            gender = binding.edtGender.text.toString()
        ), onSuccess = {
            findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
        }, onFailure = { e ->
            showToast(e.localizedMessage?.toString() ?: "DB Error")
        })
    }

    private fun setData() {
        FirestoreUtil.getUserData(onSuccess = { user ->
            binding.edtName.setText(user.name)
            binding.edtUserName.setText(user.userName)
            binding.ivImage.loadImageFromUrl(user.image)
            if (!user.age.isNullOrEmpty()) binding.edtAge.setText(user.age)
            binding.edtGender.setText(user.gender)
        }, onFailure = { e ->
            // Handle error
            showToast(e.localizedMessage?.toString() ?: "DB Error")
        })
    }

    override fun onResume() {
        super.onResume()

        setData()

    }

    private fun setGenderList() {
        binding.edtGender.setArrayAdapter(genderList)
        binding.edtGender.setOnItemClickListener { _, _, position, _ ->
            gender = genderList[position]
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}