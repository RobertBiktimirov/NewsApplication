package ru.intercommunication.newsapplication.feature.main.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.intercommunication.newsapplication.core.di.ViewModelBuilderModule
import ru.intercommunication.newsapplication.core.di.ViewModelKey
import ru.intercommunication.newsapplication.feature.main.ui.main.MainViewModel
import ru.intercommunication.newsapplication.feature.main.ui.search.SearchViewModel

@Module(includes = [ViewModelBuilderModule::class])
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

}