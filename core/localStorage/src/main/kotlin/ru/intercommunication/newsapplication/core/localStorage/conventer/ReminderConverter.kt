package ru.intercommunication.newsapplication.core.localStorage.conventer

import androidx.room.TypeConverter
import ru.intercommunication.newsapplication.core.localStorage.dto.ReminderTimeDto

class ReminderConverter {

    @TypeConverter
    fun fromReminder(importance: ReminderTimeDto) = importance.name

    @TypeConverter
    fun toReminder(value: String) = enumValueOf<ReminderTimeDto>(value)
}