package ru.intercommunication.newsapplication.feature.main.di

import dagger.Component
import ru.intercommunication.newsapplication.feature.main.di.dependencies.MainDependencies
import javax.inject.Scope

@MainFeatureScope
@Component(dependencies = [MainDependencies::class])
interface MainComponent {


    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }

}

@Scope
annotation class MainFeatureScope