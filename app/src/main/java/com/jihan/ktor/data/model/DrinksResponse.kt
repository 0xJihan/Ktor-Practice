package com.jihan.ktor.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DrinksResponse(
    val drinks: List<Drink>
)