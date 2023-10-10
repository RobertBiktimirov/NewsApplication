package ru.intercommunication.newsapplication.application

import android.app.Application
import android.content.Context
import ru.intercommunication.newsapplication.di.AppComponent
import ru.intercommunication.newsapplication.di.DaggerAppComponent

class NewsApplication : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = requireNotNull(_appComponent) {
            "AppComponent must be not null"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(this)
    }


}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NewsApplication -> appComponent
        else -> (applicationContext as NewsApplication).appComponent
    }