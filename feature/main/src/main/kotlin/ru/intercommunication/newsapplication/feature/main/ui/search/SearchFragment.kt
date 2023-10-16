package ru.intercommunication.newsapplication.feature.main.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.main.R
import ru.intercommunication.newsapplication.feature.main.databinding.FragmentSearchBinding
import ru.intercommunication.newsapplication.feature.main.di.storage.MainComponentStorage
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.ui.NewsListAdapter
import ru.intercommunication.newsapplication.navigation.AppNavigationDirections
import javax.inject.Inject


class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding ?: error("binding not must be null")


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val searchViewModel: SearchViewModel by viewModels { viewModelFactory }

    private val newsListAdapter by lazy {
        NewsListAdapter(::newsClickHandler, ::favoriteClickHandler, ::favoriteWithSave)
    }

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
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

//        openKeyboard()

        binding.searchKeyword.doAfterTextChanged {
            searchViewModel.setQuery(it.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.newsList.collect {
                    newsListAdapter.submitList(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.toDetails.collect {
                    it?.let {
                        searchViewModel.removeToDetails()
                        findNavController().navigate(
                            AppNavigationDirections.actionGlobalDetailsNavigation(it)
                        )
                    }

                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.emptyNews.collect {
                    when (it) {
                        NewsEmptyState.NewsListEmpty -> binding.newsEmpty.text =
                            getString(R.string.news_empty)

                        NewsEmptyState.NoInternet -> binding.newsEmpty.text =
                            getString(R.string.no_internet)

                        NewsEmptyState.Normal -> binding.newsEmpty.isVisible = false
                    }
                }
            }
        }
    }

    private fun openKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

        binding.searchKeyword.isActivated = true
    }

    private fun setupRecyclerView() {
        binding.newsList.adapter = newsListAdapter
        binding.newsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun favoriteWithSave(articleModel: ArticleModel) {
        searchViewModel.updateFavorite(articleModel)
    }

    private fun favoriteClickHandler(isFavorite: Boolean, id: Int) {}

    private fun newsClickHandler(articleModel: ArticleModel) {
        searchViewModel.saveHandler(articleModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}