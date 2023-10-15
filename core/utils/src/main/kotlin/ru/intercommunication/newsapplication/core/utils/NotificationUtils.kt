package ru.intercommunication.newsapplication.core.utils

interface AlarmScheduler<T> {
    fun schedule(t: T, time: Long)

    fun cancel(t: T)
}