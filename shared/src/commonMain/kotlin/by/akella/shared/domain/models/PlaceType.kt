package by.akella.shared.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PlaceType {
    @SerialName("cafe")
    CAFE,
    @SerialName("shop")
    SHOP,
    @SerialName("service")
    SERVICE,
    @SerialName("quest")
    QUEST,
    @SerialName("other")
    OTHER
}