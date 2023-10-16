package ru.intercommunication.newsapplication.feature.main.ui.search

sealed interface NewsEmptyState {
    data object NoInternet: NewsEmptyState

    data object NewsListEmpty: NewsEmptyState

    data object Normal: NewsEmptyState
}