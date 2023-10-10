package ru.intercommunication.newsapplication.core.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceNetworkDto(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String
)