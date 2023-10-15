package ru.intercommunication.newsapplication.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import ru.intercommunication.newsapplication.R
import ru.intercommunication.newsapplication.di.AppComponent
import ru.intercommunication.newsapplication.di.DaggerAppComponent
import ru.intercommunication.newsapplication.feature.details.di.dependencies.DetailsDependenciesStore
import ru.intercommunication.newsapplication.feature.main.di.dependencies.MainDependenciesStore


const val CHANNEL_ID = "NEWS-NOTIFY"
const val CHANNEL_NAME = "News App Notification"

class NewsApplication : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = requireNotNull(_appComponent) {
            "AppComponent must be not null"
        }

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(this)

        MainDependenciesStore.dependencies = appComponent
        DetailsDependenciesStore.dependencies = appComponent

        val mChannel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        mChannel.description = getString(R.string.notification_description)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NewsApplication -> appComponent
        else -> (applicationContext as NewsApplication).appComponent
    }