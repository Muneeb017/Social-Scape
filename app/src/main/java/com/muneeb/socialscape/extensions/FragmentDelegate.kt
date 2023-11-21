package com.flashbid.luv.extensions

import android.graphics.Color
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFadeThrough
import com.muneeb.socialscape.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifecycleOwnerLiveDataObserver =
                Observer<LifecycleOwner?> {
                    val viewLifecycleOwner = it ?: return@Observer

                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }

//            override fun onCreate(owner: LifecycleOwner) {
//                fragment.viewLifecycleOwnerLiveData.observeForever(
//                    viewLifecycleOwnerLiveDataObserver
//                )
//                fragment.enterTransition = MaterialFadeThrough()
//                fragment.sharedElementEnterTransition = MaterialContainerTransform().apply {
//                    drawingViewId = R.id.nav_host_fragment_activity_main
//                    scrimColor = Color.TRANSPARENT
//                    setAllContainerColors(fragment.getColorFromAttr(com.google.android.material.R.attr.backgroundColor))
//                }
//            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(
                    viewLifecycleOwnerLiveDataObserver
                )
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentDelegate(this, viewBindingFactory)
