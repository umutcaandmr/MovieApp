package com.umutdemir.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.databinding.FragmentMovieDetailBinding
import com.umutdemir.movieapp.util.downloadFromUrl
import com.umutdemir.movieapp.viewmodel.MovieDetailViewModel

class MovieDetailFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailBinding
    private var id : Int = 0
    private lateinit var viewModel: MovieDetailViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            id = MovieDetailFragmentArgs.fromBundle(it).movieId
        }

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.getDataFromAPI(id)

        viewModel.moveiDetail.observe(viewLifecycleOwner){
            it?.let { movie ->
                binding.movieName.text = movie.title
                binding.movieOriginialName.text = movie.original_title
                binding.movieBudget.text = "bUDGET : ${movie.budget}$"
                binding.movieImage.downloadFromUrl(movie.poster_path,null)
                binding.movieDate.text = "Release Date : ${movie.release_date}"
                binding.movieOverview.text = movie.overview
                binding.movieVote.text = "Vote : ${String.format("%.1f", movie.vote_average)}/10 (${movie.vote_count})"

                binding.movieTime.text = "Time : ${movie.runtime} minutes"
                binding.movieOverview.text = "Overview\n${movie.overview}"

            }
        }


    }


}