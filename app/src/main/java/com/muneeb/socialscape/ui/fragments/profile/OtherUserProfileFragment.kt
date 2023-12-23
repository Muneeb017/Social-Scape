package com.muneeb.socialscape.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.databinding.FragmentOtherUserProfileBinding
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.loadImageFromUrl

class OtherUserProfileFragment : Fragment(R.layout.fragment_other_user_profile) {

    private val binding by viewBinding(FragmentOtherUserProfileBinding::bind)
    private val args by navArgs<OtherUserProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = args.user.name
        binding.tvUserName.text = args.user.userName
        binding.ivPerson.loadImageFromUrl(args.user.image)

        binding.tvCountFollowing.setText((args.user.followings?.size?:0).toString())
        binding.tvCountFollowers.setText((args.user.followers?.size?:0).toString())

        if (args.user.isFollowed == true){
            binding.btnFollow.setText("Following")
        }else{
            binding.btnFollow.setText("Follow")
        }

        binding.btnFollow.setOnClickListener {
            if (binding.btnFollow.text == "Following") {
                FirestoreUtil.unfollowUser(userIdToUnfollow = args.user.id.toString(), onSuccess = {
                    binding.btnFollow.setText("Follow")
                    // Handle successful unfollow
                }, onFailure = { e ->
                    // Handle error
                })
            } else {
                FirestoreUtil.followUser(userIdToFollow = args.user.id.toString(), onSuccess = {
                    binding.btnFollow.setText("Following")
                }, onFailure = { e ->
                    // Handle error
                })
            }
        }
    }

}