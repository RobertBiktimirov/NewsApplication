package ru.intercommunication.newsapplication.feature.main.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
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

    val newsList: Flow<List<ArticleModel>> = repository.getNews()

    fun updateFavorite(isFavorite: Boolean, id: Int) {
        viewModelScope.launch {
            repository.updateStateFavorite(isFavorite, id)
        }
    }
}