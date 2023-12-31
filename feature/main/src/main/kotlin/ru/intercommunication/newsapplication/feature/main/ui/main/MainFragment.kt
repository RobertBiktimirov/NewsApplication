package ru.intercommunication.newsapplication.feature.main.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.main.R
import ru.intercommunication.newsapplication.feature.main.databinding.FragmentMainBinding
import ru.intercommunication.newsapplication.feature.main.di.storage.MainComponentStorage
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.ui.NewsListAdapter
import ru.intercommunication.newsapplication.navigation.AppNavigationDirections
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("binding not must be null")

    private val newsAdapter by lazy {
        NewsListAdapter(::newsClickHandler, ::favoriteClickHandler)
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
                    val oldList = newsAdapter.currentList
                    if (news.size > oldList.size) binding.newsList.scrollToPosition(0)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.isFavorite.collectLatest { flag ->
                    val list = if (flag) {
                        binding.turned.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.favorite_button
                            )
                        )
                        mainViewModel.newsList.value.filter { it.isFavorite }
                    } else {
                        binding.turned.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.not_favorite_button
                            )
                        )
                        mainViewModel.newsList.value
                    }
                    newsAdapter.submitList(list)
                }
            }
        }

        binding.turned.setOnClickListener {
            mainViewModel.onlyFavorite = !mainViewModel.onlyFavorite
        }

        binding.search.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
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
        findNavController().navigate(
            AppNavigationDirections.actionGlobalDetailsNavigation(
                articleModel.id
            )
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}