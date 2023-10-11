package ru.intercommunication.newsapplication.core.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelBuilderModule {
    @Binds
    fun bindViewModelFactory(
        factory: ViewModelFactory,
    ): ViewModelProvider.Factory
}