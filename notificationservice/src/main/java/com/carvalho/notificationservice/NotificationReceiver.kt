package com.carvalho.notificationservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

open class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")

        Log.d("NotificationReceiver", "Broadcast recebido com título: $title")
        Log.d("NotificationService", "Serviço iniciado")

        val serviceIntent = createServiceIntent(context, title, message)
        context.startService(serviceIntent)
    }

    open fun createServiceIntent(context: Context, title: String?, message: String?): Intent {
        return Intent(context, NotificationService::class.java).apply {
            putExtra("title", title)
            putExtra("message", message)
        }
    }
}
