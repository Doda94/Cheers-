package com.doda.cheers.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CheersApiService {

    @GET("random.php")
    fun getRandomCocktail(): Call<CocktailResponse>

    @GET("search.php")
    fun searchCocktail(@Query("s") drink: String): Call<CocktailResponse>
}