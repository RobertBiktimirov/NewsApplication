package ru.intercommunication.newsapplication.feature.main.di.module

import dagger.Binds
import dagger.Module
import ru.intercommunication.newsapplication.feature.main.data.repositories.MainRepositoryImpl
import ru.intercommunication.newsapplication.feature.main.di.MainFeatureScope
import ru.intercommunication.newsapplication.feature.main.domain.repositories.MainRepository

@Module
internal interface MainModule {

    @[Binds MainFeatureScope]
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository
}