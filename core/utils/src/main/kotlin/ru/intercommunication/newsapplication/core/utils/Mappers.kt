package ru.intercommunication.newsapplication.core.utils

interface Mapper<I, O> : OneWayMapper<I, O> {
    override fun map(from: I): O
    fun reverseMap(from: O): I
}

interface OneWayMapper<I, O> {
    fun map(from: I): O
}