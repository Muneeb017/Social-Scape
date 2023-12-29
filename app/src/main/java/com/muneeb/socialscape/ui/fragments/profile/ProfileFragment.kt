package com.muneeb.socialscape.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.MyPostAdapter
import com.muneeb.socialscape.adapters.StoryAdapter
import com.muneeb.socialscape.databinding.FragmentProfileBinding
import com.muneeb.socialscape.extensions.setGridLayout
import com.muneeb.socialscape.extensions.setHorizontalLayout
import com.muneeb.socialscape.model.Post
import com.muneeb.socialscape.model.local.StoryModel
import com.muneeb.socialscape.utils.FirestoreUtil
import com.muneeb.socialscape.utils.loadImageFromUrl

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val myPostAdapter by lazy { MyPostAdapter(list, this::onItemClick) }
    private val list: ArrayList<Post> = ArrayList()
    private val storyAdapter by lazy { StoryAdapter(lists) }
    private val lists: ArrayList<StoryModel> = ArrayList()

    private fun onItemClick(item: Post) {
//        showDialogDeletePost(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSetting.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingFragment)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.rcvStorie.apply {
            setHorizontalLayout()
            adapter = storyAdapter
        }
        val storyList = mutableListOf(
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194"),
            StoryModel("", "https://firebasestorage.googleapis.com/v0/b/social-scape-8b74b.appspot.com/o/images%2F1703109868833?alt=media&token=06ebeb0e-c5e7-41da-9cfb-ea451ff69194")
        )
        binding.rcvStorie.adapter = StoryAdapter(storyList)

        binding.rcvSearchDiscover.apply {
            setGridLayout(2)
            adapter = myPostAdapter
        }

        FirestoreUtil.getUploadedPosts(onSuccess = { postsList ->
            myPostAdapter.refresh(postsList.toCollection(ArrayList()))
        }, onFailure = { e ->
            // Handle error
        })

    }

    private fun setData() {
        FirestoreUtil.getUserData(onSuccess = { user ->
            if (user != null) {
                binding.tvName.text = user.name
                binding.tvUserName.text = user.userName
                binding.tvBio.text = user.bio
                binding.ivPerson.loadImageFromUrl(user.image)
                binding.tvCountFollowing.setText((user.followings?.size ?: 0).toString())
                binding.tvCountFollowers.setText((user.followers?.size ?: 0).toString())
            } else {
                Toast.makeText(requireContext(), "User dose not exist", Toast.LENGTH_SHORT).show()
            }
        }, onFailure = { e ->
            // Handle error
        })
    }

    override fun onResume() {
        super.onResume()

        setData()
    }

//    private fun showDialogDeletePost(item: Post) {
//        val builder = AlertDialog.Builder(requireContext())
//        val dialogBinding = DailogDeleteBinding.inflate(layoutInflater)
//        val dialog = builder.create().apply {
//            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            setCancelable(false)
//            setView(dialogBinding.root)
//        }
//
//        dialogBinding.btnCancel.setOnClickWithDebounce { dialog.dismiss() }
//
//        dialog.show()
//    }

}