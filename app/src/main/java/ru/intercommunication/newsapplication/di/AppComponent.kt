package ru.intercommunication.newsapplication.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.intercommunication.newsapplication.di.modules.AppModule
import ru.intercommunication.newsapplication.di.modules.NetworkModule
import ru.intercommunication.newsapplication.di.modules.RoomModule
import javax.inject.Scope

@ApplicationScope
@Component(modules = [AppModule::class, NetworkModule::class, RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@Scope
annotation class ApplicationScope