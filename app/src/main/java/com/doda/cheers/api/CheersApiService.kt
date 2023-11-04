package com.doda.cheers.api

import com.doda.cheers.CocktailResponse
import retrofit2.Call
import retrofit2.http.GET

interface CheersApiService {

    @GET("random.php")
    fun getRandomCocktail(): Call<CocktailResponse>
}