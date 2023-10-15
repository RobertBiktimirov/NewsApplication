package ru.intercommunication.newsapplication.ui.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import ru.intercommunication.newsapplication.core.di.ApplicationContext
import ru.intercommunication.newsapplication.core.utils.AlarmScheduler
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.ui.receiver.AlarmReceiver
import javax.inject.Inject

const val ARTICLE_INTENT_NAME = "article"

class AlarmSchedulerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    AlarmScheduler<ArticleModel> {

    private val manager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun schedule(t: ArticleModel, time: Long) {

        Log.d("notification_test", "from AlarmSchedulerImpl")

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(ARTICLE_INTENT_NAME, t)
        }

        val pending = PendingIntent.getBroadcast(
            context,
            t.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 0L, pending)
    }

    override fun cancel(t: ArticleModel) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingID = t.hashCode()
        val pending = PendingIntent.getBroadcast(
            context,
            pendingID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        manager.cancel(pending)
    }
}