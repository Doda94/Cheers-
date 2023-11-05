package com.doda.cheers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.doda.cheers.BuildConfig
import com.doda.cheers.CheersApplication
import com.doda.cheers.FileUtil
import com.doda.cheers.databinding.FragmentAddCocktailBottomSheetBinding
import com.doda.cheers.db.LocalCocktail
import com.doda.cheers.viewModel.LocalViewModel
import com.doda.cheers.viewModel.LocalViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import kotlin.random.Random
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody

class AddCocktailBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddCocktailBottomSheetBinding? = null

    private val binding get() = _binding!!

    val viewModel: LocalViewModel by viewModels {
        LocalViewModelFactory((activity?.application as CheersApplication).database)
    }

    private var ingredients: String = ""

    lateinit var photoFile: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCocktailBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addIngredientButton.setOnClickListener {
            ingredients += binding.ingredientEditText.text.toString() + "\n"
            binding.ingredientEditText.text?.clear()
            binding.quantityEditText.text?.clear()
        }

        binding.addPhotoButton.setOnClickListener {
            openCamera()
            val name = binding.nameEditText.text.toString()
            val instructions = binding.instrcutionsEditText.text.toString()
            val ingredients = ingredients
            val photo = photoFile.canonicalPath
            val id = (1..1000).random().toString()
            viewModel.insertLocal(LocalCocktail(id, name, instructions, ingredients, photo))
            dismiss()
        }

    }

    private fun openCamera() {
        photoFile = FileUtil.createImageFile(requireContext())!!
        FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", photoFile)
        photoFile.let {
            val fileUri = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", it)
            resultLauncher.launch(fileUri)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                val file = FileUtil.getImageFile(requireContext())
                val fileString = file.toString()
            }
        }

}