package com.muneeb.socialscape.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.AccountsAdapter
import com.muneeb.socialscape.databinding.FragmentSearchBinding
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.extensions.show
import com.muneeb.socialscape.model.User
import com.muneeb.socialscape.utils.FirestoreUtil

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val list: ArrayList<User> = ArrayList()
    private val accountsAdapter by lazy { AccountsAdapter(list, this::onUserClick) }

    private fun onUserClick(item: User) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToOtherUserProfileFragment(item)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAccounts.setOnClickListener {
            binding.constraintLayout5.show()
            binding.rcvUserPAccounts.apply {
                setVerticalLayout()
                adapter = accountsAdapter
            }
        }

        FirestoreUtil.getAllUsers(onSuccess = { userList ->
            list.clear()
            list.addAll(userList)
            accountsAdapter.refresh()
        }, onFailure = { e ->
            // Handle error
        })

    }
}