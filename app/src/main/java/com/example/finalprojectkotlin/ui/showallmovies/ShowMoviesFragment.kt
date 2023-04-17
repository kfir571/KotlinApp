package com.example.finalprojectkotlin.ui.showallmovies

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectkotlin.R
import com.example.finalprojectkotlin.databinding.ShowMoviesBinding
import com.example.finalprojectkotlin.ui.moviesViewModel

class ShowMoviesFragment:Fragment() {
    private var _binding : ShowMoviesBinding? = null

    private val binding get() = _binding!!

    private val viewModel : moviesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = ShowMoviesBinding.inflate(inflater,container,false)

        binding.fab.setOnClickListener {

            findNavController().navigate(R.id.action_showMoviesFragment_to_addMovieFragment2)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("movie")?.let {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.movies?.observe(viewLifecycleOwner) {
            binding.recycler.adapter = MovieAdapter(it, object : MovieAdapter.MovieListener {

                override fun onItemClicked(index: Int) {

                    Toast.makeText(
                        requireContext(),
                        "${it[index]}", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onItemLongClicked(index: Int) {

                    viewModel.setMovie(it[index])
                    findNavController().navigate(
                        R.id.action_showMoviesFragment_to_detailsMovieFragment2,
                        bundleOf("movie" to index)
                    )
                }
            })
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())

            ItemTouchHelper(object : ItemTouchHelper.Callback() {

                override fun getMovementFlags(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ) = makeFlag(
                    ItemTouchHelper.ACTION_STATE_SWIPE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item =
                        (binding.recycler.adapter as MovieAdapter).movieAt(viewHolder.adapterPosition)
                    viewModel.deleteMovie(item)
                }
            }).attachToRecyclerView(binding.recycler)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
