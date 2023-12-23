package com.muneeb.socialscape.ui.fragments.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.muneeb.socialscape.R
import com.muneeb.socialscape.data.local.AppPreferences
import java.util.Timer
import kotlin.concurrent.timerTask

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val pref by lazy { AppPreferences(requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            handleUserSession()
        }, 1000)
    }

    private fun handleUserSession() {
        if (pref.isLoggedIn) {
            findNavController().navigate(R.id.homeFragment)
        } else {
            findNavController().navigate(R.id.onboardingFragment)
        }
    }
}