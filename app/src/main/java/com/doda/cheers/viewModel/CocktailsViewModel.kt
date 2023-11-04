package com.doda.cheers.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.cheers.api.ApiModule
import com.doda.cheers.api.CocktailResponse
import com.doda.cheers.model.Cocktail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CocktailsViewModel() : ViewModel() {

    private var _randomCocktailsLiveData = MutableLiveData<List<Cocktail>>()

    val randomCocktailsLiveData: LiveData<List<Cocktail>> = _randomCocktailsLiveData

    fun addRandomCocktail() {
        ApiModule.retrofit.getRandomCocktail().enqueue(object : Callback<CocktailResponse> {
            override fun onResponse(call: Call<CocktailResponse>, response: Response<CocktailResponse>) {
                _randomCocktailsLiveData.value = response.body()?.drinks
            }

            override fun onFailure(call: Call<CocktailResponse>, t: Throwable) {
                TODO()
            }
        })
    }

    //znam da sam ovo mogao i bolje napisati, ali nije mi se guglalo sad :))))) TODO()
    fun getIngridients(cocktail: Cocktail): String {
        var ingridients = ""
        if (cocktail.strIngredient1 != null)
            ingridients += cocktail.strIngredient1 + " " + cocktail.strMeasure1 + "\n"
        if (cocktail.strIngredient2 != null)
            ingridients += cocktail.strIngredient2 + " " + cocktail.strMeasure2 + "\n"
        if (cocktail.strIngredient3 != null)
            ingridients += cocktail.strIngredient3 + " " + cocktail.strMeasure3 + "\n"
        if (cocktail.strIngredient4 != null)
            ingridients += cocktail.strIngredient4 + " " + cocktail.strMeasure4 + "\n"
        if (cocktail.strIngredient5 != null)
            ingridients += cocktail.strIngredient5 + " " + cocktail.strMeasure5 + "\n"
        if (cocktail.strIngredient6 != null)
            ingridients += cocktail.strIngredient6 + " " + cocktail.strMeasure6 + "\n"
        if (cocktail.strIngredient7 != null)
            ingridients += cocktail.strIngredient7 + " " + cocktail.strMeasure7 + "\n"
        if (cocktail.strIngredient8 != null)
            ingridients += cocktail.strIngredient8 + " " + cocktail.strMeasure8 + "\n"
        if (cocktail.strIngredient9 != null)
            ingridients += cocktail.strIngredient9 + " " + cocktail.strMeasure9 + "\n"
        if (cocktail.strIngredient10 != null)
            ingridients += cocktail.strIngredient10 + " " + cocktail.strMeasure10 + "\n"
        if (cocktail.strIngredient11 != null)
            ingridients += cocktail.strIngredient11 + " " + cocktail.strMeasure11 + "\n"
        if (cocktail.strIngredient12 != null)
            ingridients += cocktail.strIngredient12 + " " + cocktail.strMeasure12 + "\n"
        if (cocktail.strIngredient13 != null)
            ingridients += cocktail.strIngredient13 + " " + cocktail.strMeasure13 + "\n"
        if (cocktail.strIngredient14 != null)
            ingridients += cocktail.strIngredient14 + " " + cocktail.strMeasure14 + "\n"
        if (cocktail.strIngredient15 != null)
            ingridients += cocktail.strIngredient15 + " " + cocktail.strMeasure15 + "\n"
        return ingridients
    }

}