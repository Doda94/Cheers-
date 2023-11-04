package com.doda.cheers.api

import com.doda.cheers.model.Cocktail
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CocktailResponse(
    @SerialName("drinks") val drinks: List<Cocktail>
)
