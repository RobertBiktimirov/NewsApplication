package ru.intercommunication.newsapplication.di.modules

import dagger.Binds
import dagger.Module
import ru.intercommunication.newsapplication.core.utils.AlarmScheduler
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.ui.notification.AlarmSchedulerImpl


@Module
interface NotificationModule {

    @Binds
    fun bindAlarmScheduler(impl: AlarmSchedulerImpl): AlarmScheduler<ArticleModel>
}