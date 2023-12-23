package com.muneeb.socialscape.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.NewPostAdapter
import com.muneeb.socialscape.databinding.FragmentHomeBinding
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.utils.FirestoreUtil

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val newPostAdapter by lazy { NewPostAdapter(list) }
    private val list: ArrayList<Post> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivNewPost.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newPostFragment)
        }
        binding.ivNotification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_likesFragment)
        }
//        binding.rcvStorie.apply {
////            setHorizontalLayout()
////            adapter = storyAdapter
////        }
////        val storyList = mutableListOf(
////            StoryModel("Muneeb", "url_to_image_1"),
////            StoryModel("Mubeen", "url_to_image_2"),
////            StoryModel("Muneeb", "url_to_image_3"),
////            StoryModel("Mubeen", "url_to_image_4"),
////            StoryModel("Muneeb", "url_to_image_5"),
////            StoryModel("Mubeen", "url_to_image_6"),
////            StoryModel("Muneeb", "url_to_image_7"),
////            StoryModel("Mubeen", "url_to_image_8"),
////            StoryModel("Muneeb", "url_to_image_9"),
////            StoryModel("Mubeen", "url_to_image_10")
////        )

        binding.rcvUserPost.apply {
            setVerticalLayout()
            adapter = newPostAdapter
        }

        FirestoreUtil.getPostsFromFollowedUsers(
            onSuccess = { postsList ->
                // Handle the list of posts
                list.clear()
                list.addAll(postsList)
                newPostAdapter.refresh()
            },
            onFailure = { e ->
                // Handle error
            }
        )




    }
}