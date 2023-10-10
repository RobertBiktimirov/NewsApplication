package ru.intercommunication.newsapplication.core.ui

interface EventHandler<in T> {
    fun obtainEvent(event: T)
}