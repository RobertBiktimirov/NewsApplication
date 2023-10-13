package ru.intercommunication.newsapplication.feature.details.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.intercommunication.newsapplication.core.di.ViewModelBuilderModule
import ru.intercommunication.newsapplication.core.di.ViewModelKey
import ru.intercommunication.newsapplication.feature.details.ui.DetailsViewModel

@Module(includes = [ViewModelBuilderModule::class])
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

}