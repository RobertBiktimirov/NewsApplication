package ru.intercommunication.newsapplication.ui.receiver

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ru.intercommunication.newsapplication.R
import ru.intercommunication.newsapplication.feature.details.domain.models.ArticleModel
import ru.intercommunication.newsapplication.ui.MainActivity
import ru.intercommunication.newsapplication.ui.notification.ARTICLE_INTENT_NAME

class AlarmReceiver : BroadcastReceiver() {

    private companion object {
        const val CHANNEL_ID = "NEWS-NOTIFY"
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        val article = intent?.getParcelableExtra<ArticleModel>(ARTICLE_INTENT_NAME) ?: return

        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            putExtra("bundle_news_id", article.id)
        }

        val notification = Notification.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(article.title)
            .setContentText(article.description)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    article.hashCode(),
                    contentIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setAutoCancel(true)
            .build()


        manager.notify(article.hashCode(), notification)
    }
}