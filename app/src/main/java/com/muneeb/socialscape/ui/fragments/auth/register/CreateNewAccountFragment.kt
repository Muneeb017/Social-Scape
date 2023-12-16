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
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentCreateNewAccountBinding
import com.muneeb.socialscape.databinding.FragmentHomeBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce

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

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                findNavController().navigate(R.id.action_createNewAccountFragment_to_loginFragment)
                            } else {
                                Toast.makeText(
                                    requireContext(), it.exception.toString(), Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                } else {
                    Toast.makeText(requireContext(), "Password is not matching", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    requireContext(), "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT
                ).show()
            }

        }


    }

}