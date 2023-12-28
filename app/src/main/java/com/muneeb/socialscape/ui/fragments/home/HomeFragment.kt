package com.muneeb.socialscape.ui.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.NewPostAdapter
import com.muneeb.socialscape.adapters.StoryAdapter
import com.muneeb.socialscape.databinding.FragmentHomeBinding
import com.muneeb.socialscape.extensions.setHorizontalLayout
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.model.StoryModel
import com.muneeb.socialscape.utils.FirestoreUtil

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val newPostAdapter by lazy { NewPostAdapter(list,this::onItemClick) }
    private val list: ArrayList<Post> = ArrayList()
    private val storyAdapter by lazy { StoryAdapter(lists) }
    private val lists: ArrayList<StoryModel> = ArrayList()

    private fun onItemClick(item: Post) {
        // Assuming you have a Post model with a postId property
        val postId = "your_post_id_here"

        FirestoreUtil.likePost(postId,
            onSuccess = {
                // Liked successfully, update UI or perform other actions
                Log.d("FirestoreUtil", "Post liked successfully")
            },
            onFailure = { exception ->
                // Handle failure, show an error message, log, etc.
                Log.e("FirestoreUtil", "Error liking post", exception)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivNewPost.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_videoRecordingFragment)
        }
        binding.ivNotification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_likesFragment)
        }
        binding.rcvStorie.apply {
            setHorizontalLayout()
            adapter = storyAdapter
        }
        val storyList = mutableListOf(
            StoryModel("Muneeb", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Mubeen", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Muneeb", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Mubeen", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Muneeb", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Mubeen", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Muneeb", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Mubeen", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Muneeb", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("Mubeen", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194")
        )
        binding.rcvStorie.adapter = StoryAdapter(storyList)

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