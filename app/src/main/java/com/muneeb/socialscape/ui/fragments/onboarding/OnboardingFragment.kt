package com.muneeb.socialscape.ui.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.flashbid.luv.extensions.viewBinding
import com.muneeb.socialscape.R
import com.muneeb.socialscape.adapters.FragmentPagerAdapter
import com.muneeb.socialscape.databinding.FragmentOnboardingBinding
import com.muneeb.socialscape.extensions.setOnClickWithDebounce

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
//    private lateinit var onBoardAdapter: FragmentPagerAdapter
//
//    private var myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
//        override fun onPageSelected(position: Int) {
//
//            val itemIndex = binding.viewPager.currentItem
//            if (itemIndex == 3) {
//                binding.nextButton.text = getString(R.string.on_board_start)
//                binding.nextButton.padding(
//                    setOf(Edge.RIGHT, Edge.LEFT),
//                    resources.getDimensionPixelOffset(com.intuit.sdp.R.dimen._60sdp)
//                )
//            } else {
//                binding.nextButton.text = getString(R.string.on_board_Next)
//                binding.nextButton.padding(
//                    setOf(Edge.RIGHT, Edge.LEFT),
//                    resources.getDimensionPixelOffset(com.intuit.sdp.R.dimen._30sdp)
//                )
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }

        binding.btnSinUp.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_createNewAccountFragment)
        }


//        val list: List<Fragment> =
//            ArrayList(
//                listOf(
//                    OnboardingFirstPageFrag(),
//                    OnboardingSecondPageFrag(),
//                    OnboardingThirdPageFrag()
//                )
//            )
//        onBoardAdapter = FragmentPagerAdapter(childFragmentManager, lifecycle, list)
//
//        binding.viewPager.isUserInputEnabled = true
//        binding.viewPager.adapter = onBoardAdapter
//
//        binding.nextButton.setOnClickWithDebounce {
//            var itemIndex = binding.viewPager.currentItem
//            if (itemIndex != 3) {
//                itemIndex += 1
//                binding.viewPager.currentItem = itemIndex
//                binding.nextButton.text = getString(R.string.on_board_Next)
//
//                binding.nextButton.padding(
//                    setOf(Edge.RIGHT, Edge.LEFT),
//                )
//                if (itemIndex == 3) {
//                    binding.nextButton.text = getString(R.string.on_board_start)
//                    binding.nextButton.padding(
//                        setOf(Edge.RIGHT, Edge.LEFT),
//                    )
//                }
//            } else findNavController().navigate(R.id.chooseFavFragment)
//
//        }
//
//        binding.skipButton.setOnClickWithDebounce {
//            findNavController().navigate(R.id.chooseFavFragment)
//        }
//
//        binding.viewPager.registerOnPageChangeCallback(myPageChangeCallback)
    }

}