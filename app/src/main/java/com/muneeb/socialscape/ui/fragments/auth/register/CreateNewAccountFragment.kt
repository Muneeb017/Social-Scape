package com.muneeb.socialscape.ui.fragments.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentCreateNewAccountBinding
import com.muneeb.socialscape.databinding.FragmentHomeBinding

class CreateNewAccountFragment : Fragment(R.layout.fragment_create_new_account) {

    private val binding by viewBinding(FragmentCreateNewAccountBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}