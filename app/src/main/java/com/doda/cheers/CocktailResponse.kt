package com.doda.cheers

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CocktailResponse(
    @SerialName("drinks") val drinks: List<Cocktail>
)
