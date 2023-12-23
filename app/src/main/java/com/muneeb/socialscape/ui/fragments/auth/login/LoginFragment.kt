package com.muneeb.socialscape.ui.fragments.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.muneeb.socialscape.R
import com.muneeb.socialscape.data.local.AppPreferences
import com.muneeb.socialscape.databinding.FragmentCreateNewAccountBinding
import com.muneeb.socialscape.databinding.FragmentLoginBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private lateinit var firebaseAuth: FirebaseAuth
    private val pref by lazy { AppPreferences(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickWithDebounce {
            findNavController().popBackStack()
        }

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createNewAccountFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPass.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            pref.isLoggedIn = true
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            Toast.makeText(
                                requireContext(), it.exception.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            } else {
                Toast.makeText(
                    requireContext(), "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}