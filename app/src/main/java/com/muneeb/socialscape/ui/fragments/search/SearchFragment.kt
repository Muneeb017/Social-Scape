package com.muneeb.socialscape.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.AccountsAdapter
import com.muneeb.socialscape.databinding.FragmentSearchBinding
import com.muneeb.socialscape.extensions.setVerticalLayout
import com.muneeb.socialscape.model.OtherUser
import com.muneeb.socialscape.utils.FirestoreUtil

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val accountsAdapter = AccountsAdapter(this::onUserClick)

    private fun onUserClick(item: OtherUser) {
        val action = SearchFragmentDirections.actionSearchFragmentToOtherUserProfileFragment(item)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvUserPAccounts.apply {
            setVerticalLayout()
            adapter = accountsAdapter
        }

        binding.edtSearch.doAfterTextChanged {
            FirestoreUtil.searchUsers(query = it.toString(), onSuccess = { matchingUsersList ->
                // Handle the list of matching users
                accountsAdapter.refresh(matchingUsersList.toCollection(ArrayList()))
            }, onFailure = { e ->
                // Handle error
            })
        }

    }
}