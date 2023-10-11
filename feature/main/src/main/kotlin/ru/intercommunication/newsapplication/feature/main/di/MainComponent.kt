package ru.intercommunication.newsapplication.feature.main.di

import dagger.Component
import ru.intercommunication.newsapplication.feature.main.di.dependencies.MainDependencies
import ru.intercommunication.newsapplication.feature.main.di.module.ApiModule
import ru.intercommunication.newsapplication.feature.main.di.module.DatabaseModule
import ru.intercommunication.newsapplication.feature.main.di.module.MainModule
import ru.intercommunication.newsapplication.feature.main.di.module.ViewModelModule
import ru.intercommunication.newsapplication.feature.main.ui.MainFragment
import javax.inject.Scope

@MainFeatureScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [ApiModule::class, DatabaseModule::class, MainModule::class, ViewModelModule::class]
)
internal interface MainComponent {

    fun inject(mainFragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }

}

@Scope
internal annotation class MainFeatureScope