package ru.intercommunication.newsapplication.feature.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.splash.databinding.FragmentSplashBinding
import ru.intercommunication.newsapplication.navigation.AppNavigationDirections

class SplashFragment: Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding ?: error("binding not must be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(400)
            val id = requireActivity().intent.getIntExtra("bundle_news_id", -1)
            if (id != -1) {
                findNavController().apply {
                    navigate(AppNavigationDirections.actionGlobalDetailsNavigation(id))
                }
            } else {
                findNavController().apply {
                    popBackStack()
                    navigate(AppNavigationDirections.actionGlobalMainNavigation())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}