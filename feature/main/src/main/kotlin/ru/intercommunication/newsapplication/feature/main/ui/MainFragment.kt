package ru.intercommunication.newsapplication.feature.main.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.main.databinding.FragmentMainBinding
import ru.intercommunication.newsapplication.feature.main.di.storage.MainComponentStorage
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("binding not must be null")

    private val newsAdapter by lazy {
        MainAdapter(::newsClickHandler, ::favoriteClickHandler)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        ViewModelProvider(this).get<MainComponentStorage>()
            .component
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.newsList.collect { news ->
                    newsAdapter.submitList(news)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.newsList.adapter = newsAdapter
        binding.newsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun favoriteClickHandler(isFavorite: Boolean, id: Int) {
        mainViewModel.updateFavorite(isFavorite, id)
    }

    private fun newsClickHandler(articleModel: ArticleModel) {
        Toast.makeText(requireContext(), articleModel.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}