package ru.intercommunication.newsapplication.feature.details.di.storage

import androidx.lifecycle.ViewModel
import ru.intercommunication.newsapplication.feature.details.di.DaggerDetailsComponent
import ru.intercommunication.newsapplication.feature.details.di.dependencies.DetailsDependenciesProvider

internal class DetailsComponentStorage : ViewModel() {
    val component =
        DaggerDetailsComponent.factory().create(DetailsDependenciesProvider.dependencies)
}