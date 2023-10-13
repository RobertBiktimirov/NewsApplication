package ru.intercommunication.newsapplication.feature.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.feature.details.domain.repositories.DetailsRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    private val _news: Channel<ArticleModel> = Channel(Channel.BUFFERED)
    val news = _news.receiveAsFlow()

    fun getNews(id: Int) {
        viewModelScope.launch {
            detailsRepository.getNews(id)
        }
    }

}