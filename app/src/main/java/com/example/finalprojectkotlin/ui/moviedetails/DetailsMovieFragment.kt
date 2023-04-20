package com.example.finalprojectkotlin.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.finalprojectkotlin.databinding.DetailsOneMovieBinding
import com.example.finalprojectkotlin.ui.moviesViewModel

class DetailsMovieFragment:Fragment() {


    var _binding : DetailsOneMovieBinding?  = null
    val viewModel:moviesViewModel by activityViewModels()
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsOneMovieBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.chosenMovie.observe(viewLifecycleOwner) {
            binding.movieTitle.text = it.title
            binding.movieDescription.text = it.description
            binding.movieGenre.text = it.genre
            binding.movieYear.text = it.year
            Glide.with(requireContext()).load(it.photo)
                .into(binding.itemImage)
        }
//        arguments?.getInt("movie")?.let {
//
//
//            binding.movieTitle.text = item.title
//            binding.movieDescription.text = item.description
//            binding.movieGenre.text = item.genre
//            binding.movieYear.text = item.year
//            Glide.with(requireContext()).load(item.photo).circleCrop()
//                .into(binding.itemImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}