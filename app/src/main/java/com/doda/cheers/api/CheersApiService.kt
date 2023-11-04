package com.doda.cheers.api

import retrofit2.Call
import retrofit2.http.GET

interface CheersApiService {

    @GET("random.php")
    fun getRandomCocktail(): Call<CocktailResponse>
}