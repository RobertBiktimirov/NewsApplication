package ru.intercommunication.newsapplication.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.di.ApplicationScope

@Module
class AppModule {

    @ApplicationScope
    @ApplicationContext
    @Provides
    fun provideApplicationContext(context: Context): Context {
        return context
    }
}