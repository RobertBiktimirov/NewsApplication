package ru.intercommunication.newsapplication.feature.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.repositories.DetailsRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    private val _news: MutableStateFlow<ArticleModel> = MutableStateFlow(ArticleModel.toEmpty())
    val news = _news.asStateFlow()

    fun getNews(id: Int) {
        viewModelScope.launch {
            _news.emit(detailsRepository.getNews(id))
        }
    }

    fun saveComment(comment: String) {
        viewModelScope.launch {
            detailsRepository.saveNewDataDetails(comment, news.value.id)
        }
    }

}