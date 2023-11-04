package com.doda.cheers.model

import kotlinx.serialization.Serializable

@Serializable
data class SimpleCocktail(
    val id: String,
    val name: String?,
    val instructions: String?,
    val ingredients: String?,
    val imgSrcUrl: String?,
): java.io.Serializable
