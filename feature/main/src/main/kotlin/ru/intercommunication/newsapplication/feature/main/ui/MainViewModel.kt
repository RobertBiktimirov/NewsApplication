package ru.intercommunication.newsapplication.feature.main.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.core.utils.RepositoryException
import ru.intercommunication.newsapplication.core.utils.RepositoryHttpError
import ru.intercommunication.newsapplication.core.utils.RepositorySuccess
import ru.intercommunication.newsapplication.feature.main.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.main.domain.repositories.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()


    var onlyFavorite = false
        set(value) {
            field = value
            _isFavorite.tryEmit(value)
        }

    init {
        viewModelScope.launch {
            when (val res = repository.loadScreen()) {
                is RepositoryException -> {
                    Log.d("viewModelStatus", res.toString())
                }

                is RepositoryHttpError -> {
                    Log.d("viewModelStatus", res.toString())
                }

                is RepositorySuccess -> {
                    Log.d("viewModelStatus", res.toString())
                }
            }
        }
    }

    val newsList: StateFlow<List<ArticleModel>> =
        repository.getNews().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateFavorite(isFavorite: Boolean, id: Int) {
        viewModelScope.launch {
            repository.updateStateFavorite(isFavorite, id)
        }
    }
}