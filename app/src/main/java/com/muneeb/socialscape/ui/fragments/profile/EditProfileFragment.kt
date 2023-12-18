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
import com.muneeb.socialscape.databinding.FragmentEditProfileBinding
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.setArrayAdapter

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val binding by viewBinding(FragmentEditProfileBinding::bind)
    private var gender = ""

    private val genderList by lazy {
        arrayListOf<String>().apply {
            add(getString(R.string.male))
            add(getString(R.string.female))
            add(getString(R.string.other))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSave.setOnClickListener {
            addUserUpdateDataToDB()
        }
        setGenderList()
        setData()
    }

    private fun addUserUpdateDataToDB() {
        FirestoreUtil.createOrUpdateUserData(FirestoreUtil.User(
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
            if (!user.age.isNullOrEmpty()) binding.edtAge.setText(user.age)
            binding.edtGender.setText(user.gender)
        }, onFailure = { e ->
            // Handle error
            showToast(e.localizedMessage?.toString() ?: "DB Error")
        })
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