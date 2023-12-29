package com.muneeb.socialscape.ui.fragments.likes_friend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.LikesAdapter
import com.muneeb.socialscape.databinding.FragmentLikesBinding
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.local.LikesModel

class LikesFragment : Fragment(R.layout.fragment_likes) {

    private val binding by viewBinding(FragmentLikesBinding::bind)
    private val likesAdapter by lazy { LikesAdapter(list) }
    private val list: ArrayList<LikesModel> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rcvLikes.apply {
            setVerticalLayout()
            adapter = likesAdapter
        }
        val likesList = mutableListOf(
            LikesModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "Just Now",
                "Commented on your post."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "1 hours ago",
                "Like your post."
            ), LikesModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "40 min ago",
                "Tagged you in a post."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "2 days ago",
                "Started following you."
            ), LikesModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "7 hours ago",
                "Tagged you in a post."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "4 days ago",
                "Like your post."
            ), LikesModel(
                "muneeb",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "6 days ago",
                "Commented on your post."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "2 days ago",
                "Started following you."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "2 days ago",
                "Started following you."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "2 days ago",
                "Started following you."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "2 days ago",
                "Started following you."
            ), LikesModel(
                "mubeen",
                "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194",
                "2 days ago",
                "Started following you."
            )
        )
        binding.rcvLikes.adapter = LikesAdapter(likesList)
    }

}