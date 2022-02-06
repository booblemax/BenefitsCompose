package by.akella.shared.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PromoType {
    @SerialName("badge")
    BADGE,
    @SerialName("word")
    WORD
}