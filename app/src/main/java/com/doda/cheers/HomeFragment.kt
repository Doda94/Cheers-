package com.doda.cheers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doda.cheers.api.ApiModule
import com.doda.cheers.databinding.FragmentHomeBinding
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.testButton.setOnClickListener {
            ApiModule.initRetrofit()
            testApi()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun testApi(){
         ApiModule.retrofit.getRandomCocktail().enqueue(object : Callback<CocktailResponse> {
             override fun onResponse(call: Call<CocktailResponse>, response: Response<CocktailResponse>) {
                response.body()?.let {
                    binding.testText.text = it.drinks[0].strDrink
                }
             }

             override fun onFailure(call: Call<CocktailResponse>, t: Throwable) {
                 TODO()
             }

         })
    }
}