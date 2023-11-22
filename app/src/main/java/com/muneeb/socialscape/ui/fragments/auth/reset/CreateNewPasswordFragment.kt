package com.muneeb.socialscape.ui.fragments.auth.reset

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentCreateNewPasswordBinding
import com.muneeb.socialscape.databinding.FragmentForgotPasswordBinding

class CreateNewPasswordFragment : Fragment(R.layout.fragment_create_new_password) {

    private val binding by viewBinding(FragmentCreateNewPasswordBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}