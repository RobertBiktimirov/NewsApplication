package ru.intercommunication.newsapplication.feature.details.di

import dagger.Component
import ru.intercommunication.newsapplication.feature.details.di.dependencies.DetailsDependencies
import ru.intercommunication.newsapplication.feature.details.di.modules.DetailsModule
import ru.intercommunication.newsapplication.feature.details.di.modules.ViewModelModule
import ru.intercommunication.newsapplication.feature.details.ui.detailsFragment.DetailsFragment
import javax.inject.Scope

@DetailsFeatureScope
@Component(
    dependencies = [DetailsDependencies::class],
    modules = [DetailsModule::class, ViewModelModule::class]
)
internal interface DetailsComponent {

    fun inject(detailsFragment: DetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DetailsDependencies): DetailsComponent
    }
}

@Scope
annotation class DetailsFeatureScope