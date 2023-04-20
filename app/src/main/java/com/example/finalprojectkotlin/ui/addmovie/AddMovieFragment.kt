package com.example.finalprojectkotlin.ui.addmovie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalprojectkotlin.data.model.Movie
import com.example.finalprojectkotlin.R
import com.example.finalprojectkotlin.databinding.AddMovieBinding
import com.example.finalprojectkotlin.ui.moviesViewModel

class AddMovieFragment:Fragment() {

    // binding is the connector between our UI and our data
    private var _binding : AddMovieBinding? = null

    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    // We use activityViewModels so that all our fragments will use the *same instance* of viewModel
    private val viewModel : moviesViewModel by activityViewModels()

    // This is asking for permission to access the gallery
    private var chooseImageLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            binding.resultImage.setImageURI(it)
            if (it != null) {
                requireActivity().contentResolver.takePersistableUriPermission(it,Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            imageUri = it
        }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddMovieBinding.inflate(inflater,container,false)

        // Adding the movie (with the data the user entered) to our local DB
        binding.finishBtn.setOnClickListener {
            val movie  = Movie(binding.itemTitle.text.toString(),
                binding.itemDescription.text.toString(),
                binding.itemGenre.text.toString(),
                binding.itemYearRelease.text.toString(),
                imageUri.toString())

            viewModel.addMovie(movie)

            // Going back to hone screen
            findNavController().navigate(
                R.id.action_addMovieFragment2_to_showMoviesFragment
                , bundleOf("movie" to movie)
            )

        }

        binding.resultImage.setOnClickListener {
            chooseImageLauncher.launch(arrayOf("image/*"))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}