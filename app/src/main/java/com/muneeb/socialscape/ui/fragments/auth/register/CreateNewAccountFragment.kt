package com.muneeb.socialscape.ui.fragments.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentCreateNewAccountBinding
import com.muneeb.socialscape.databinding.FragmentHomeBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.FirestoreUtil

class CreateNewAccountFragment : Fragment(R.layout.fragment_create_new_account) {

    private val binding by viewBinding(FragmentCreateNewAccountBinding::bind)


    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickWithDebounce {
            findNavController().popBackStack()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_createNewAccountFragment_to_loginFragment)
        }

        binding.btnCreateAccount.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPass.text.toString()
            val confirmPass = binding.edtPassConfirm.text.toString()

            register(email, password, confirmPass)
        }

    }

    private fun register(email: String, password: String, confirmPass: String) {
        when {
            email.isEmpty() || password.isEmpty() || confirmPass.isEmpty() -> {
                showToast("Empty Fields Are not Allowed !!")
            }

            password != confirmPass -> {
                showToast("Password is not matching")
            }

            else -> {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            addUserToDB()
                        } else {
                            showToast(task.exception?.toString() ?: "Registration failed")
                        }
                    }
            }
        }
    }

    private fun addUserToDB() {
        FirestoreUtil.createOrUpdateUserData(User(
            email = firebaseAuth.currentUser?.email, id = firebaseAuth.currentUser?.uid
        ), onSuccess = {
            findNavController().navigate(R.id.action_createNewAccountFragment_to_loginFragment)
        }, onFailure = { e ->
            showToast(e.localizedMessage?.toString() ?: "DB Error")
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}