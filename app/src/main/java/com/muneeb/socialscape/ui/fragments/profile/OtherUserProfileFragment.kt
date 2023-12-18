package com.muneeb.socialscape.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentOtherUserProfileBinding
import com.muneeb.socialscape.utils.loadImageFromUrl

class OtherUserProfileFragment : Fragment(R.layout.fragment_other_user_profile) {

    private val binding by viewBinding(FragmentOtherUserProfileBinding::bind)
    private val args by navArgs<OtherUserProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = args.user.name
        binding.tvUserName.text = args.user.userName
        binding.ivPerson.loadImageFromUrl(args.user.image)

    }

}