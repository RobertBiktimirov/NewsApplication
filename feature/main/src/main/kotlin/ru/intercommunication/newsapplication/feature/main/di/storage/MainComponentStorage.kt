package ru.intercommunication.newsapplication.feature.main.di.storage

import androidx.lifecycle.ViewModel
import ru.intercommunication.newsapplication.feature.main.di.DaggerMainComponent
import ru.intercommunication.newsapplication.feature.main.di.dependencies.MainDependenciesProvider

internal class MainComponentStorage : ViewModel() {

    val component = DaggerMainComponent.factory().create(MainDependenciesProvider.dependencies)
}