package ru.intercommunication.newsapplication.core.localStorage.dto

import androidx.room.ColumnInfo

data class SourceLocalDto(
    @ColumnInfo("id") val id: String?,
    @ColumnInfo("name") val name: String
)