package com.jihan.ktor.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Drink(
    val idDrink: Long? = null,
    val strDrink: String? = null,
    val strDrinkThumb: String? = null
)