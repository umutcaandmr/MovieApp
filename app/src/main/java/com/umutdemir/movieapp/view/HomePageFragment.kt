package com.umutdemir.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.umutdemir.movieapp.adapter.HomePageAdapter
import com.umutdemir.movieapp.databinding.FragmentHomePageBinding
import com.umutdemir.movieapp.viewmodel.HomePageViewModel


class HomePageFragment : Fragment() {


    private lateinit var binding : FragmentHomePageBinding
    private lateinit var viewmodel : HomePageViewModel
    private var popularAdapter = HomePageAdapter(arrayListOf())
    private var nowPlayingAdapter = HomePageAdapter(arrayListOf())
    private var upcomingAdapter = HomePageAdapter(arrayListOf())
    private var topRatedAdapter = HomePageAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this)[HomePageViewModel::class.java]
        viewmodel.getPopularFromAPI()
        viewmodel.getNowPlayingFromAPI()
        viewmodel.getTopRatedFromAPI()
        viewmodel.getUpcomingFromAPI()

        binding.popularRecyclerView.adapter = popularAdapter
        binding.popularRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.nowPlayingView.adapter = nowPlayingAdapter
        binding.nowPlayingView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.upcomingRecyclerView.adapter = upcomingAdapter
        binding.upcomingRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.topratedRecyclerView.adapter = topRatedAdapter
        binding.topratedRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        viewmodel.popularMovieList.observe(viewLifecycleOwner){

            it?.let {
                val responseMovie = it
                val movieList = responseMovie.results

                popularAdapter.updateData(movieList)
                binding.popularRecyclerView.visibility = View.VISIBLE
            }

        }

        viewmodel.nowPlayingMovieList.observe(viewLifecycleOwner){

            it?.let {
                val responseMovie = it
                val movieList = responseMovie.results

                nowPlayingAdapter.updateData(movieList)
                binding.nowPlayingView.visibility = View.VISIBLE
            }

        }

        viewmodel.topRatedMovieList.observe(viewLifecycleOwner){

            it?.let {
                val responseMovie = it
                val movieList = responseMovie.results

                topRatedAdapter.updateData(movieList)
                binding.topratedRecyclerView.visibility = View.VISIBLE
            }

        }

        viewmodel.upcomingMovieList.observe(viewLifecycleOwner){

            it?.let {
                val responseMovie = it
                val movieList = responseMovie.results

                upcomingAdapter.updateData(movieList)
                binding.upcomingRecyclerView.visibility = View.VISIBLE
            }

        }


        binding.swipeRefreshLayout.setOnRefreshListener {
            viewmodel.getPopularFromAPI()
            viewmodel.getUpcomingFromAPI()
            viewmodel.getTopRatedFromAPI()
            viewmodel.getNowPlayingFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }



    }

}