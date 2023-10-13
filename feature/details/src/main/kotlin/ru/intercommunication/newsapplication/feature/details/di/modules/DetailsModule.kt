package ru.intercommunication.newsapplication.feature.details.di.modules

import dagger.Binds
import dagger.Module
import ru.intercommunication.newsapplication.feature.details.data.repositories.DetailsRepositoryImpl
import ru.intercommunication.newsapplication.feature.details.di.DetailsFeatureScope
import ru.intercommunication.newsapplication.feature.details.domain.repositories.DetailsRepository

@Module
internal interface DetailsModule {

    @[DetailsFeatureScope Binds]
    fun bindDetailsRepository(impl: DetailsRepositoryImpl): DetailsRepository
}