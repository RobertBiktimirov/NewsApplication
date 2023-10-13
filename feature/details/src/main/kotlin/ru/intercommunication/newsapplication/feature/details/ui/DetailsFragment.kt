package ru.intercommunication.newsapplication.feature.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.details.databinding.FragmentDetailsBinding
import ru.intercommunication.newsapplication.feature.details.di.storage.DetailsComponentStorage
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding = _binding ?: throw RuntimeException("binding not must be null")

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailsViewModel: DetailsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        ViewModelProvider(this).get<DetailsComponentStorage>()
            .component
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}