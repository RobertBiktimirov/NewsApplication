@file:OptIn(FlowPreview::class)

package ru.intercommunication.newsapplication.feature.main.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.core.utils.RepositoryException
import ru.intercommunication.newsapplication.core.utils.RepositoryHttpError
import ru.intercommunication.newsapplication.core.utils.RepositoryResult
import ru.intercommunication.newsapplication.core.utils.RepositorySuccess
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.domain.repositories.MainRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {


    private val _searchQuery: MutableStateFlow<String> = MutableStateFlow("")

    init {
        subscribeToSearchQueryChanges()
    }

    private val _newsList = Channel<List<ArticleModel>>(Channel.BUFFERED)
    val newsList = _newsList.receiveAsFlow()

    private val _noInternet = MutableStateFlow(false)
    val noInternet = _noInternet.asStateFlow()


    private val _emptyNews: MutableStateFlow<NewsEmptyState> =
        MutableStateFlow(NewsEmptyState.NewsListEmpty)
    val emptyNews = _emptyNews.asStateFlow()

    fun setQuery(query: String) = viewModelScope.launch {
        _searchQuery.emit(query)
    }

    private val _toDetails: MutableStateFlow<Int?> = MutableStateFlow(null)
    val toDetails = _toDetails.asStateFlow()

    private fun subscribeToSearchQueryChanges() {
        _searchQuery
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .debounce(500)
            .flatMapLatest { searchNews(it) }
            .onEach { handleResult(it) }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    fun removeToDetails() {
        viewModelScope.launch {
            _toDetails.emit(null)
        }
    }

    private suspend fun searchNews(query: String): Flow<RepositoryResult<List<ArticleModel>>> =
        flow {
            if (query != "") _emptyNews.emit(NewsEmptyState.Normal)
            emit(repository.getNewsByQuery(query))
        }


    private fun handleResult(result: RepositoryResult<List<ArticleModel>>) = viewModelScope.launch {
        when (result) {
            is RepositoryException -> {
                _emptyNews.emit(NewsEmptyState.NoInternet)
            }

            is RepositoryHttpError -> {
                _emptyNews.emit(NewsEmptyState.NoInternet)
            }

            is RepositorySuccess -> {
                if (result.data.isEmpty()) {
                    _emptyNews.emit(NewsEmptyState.NewsListEmpty)
                }
                _newsList.send(result.data)
            }
        }
    }

    fun updateFavorite(articleModel: ArticleModel) {
        viewModelScope.launch {
            val article = articleModel.copy(isFavorite = !articleModel.isFavorite)
            repository.saveNews(article)
        }
    }

    fun saveHandler(articleModel: ArticleModel) {
        viewModelScope.launch {
            _toDetails.emit(repository.saveNews(articleModel))
        }
    }
}