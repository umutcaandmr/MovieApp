package com.umutdemir.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.umutdemir.movieapp.databinding.RecyclerRowHomepageBinding
import com.umutdemir.movieapp.model.Movie
import com.umutdemir.movieapp.util.downloadFromUrl
import com.umutdemir.movieapp.util.placeHolder
import com.umutdemir.movieapp.view.HomePageFragment
import com.umutdemir.movieapp.view.HomePageFragmentDirections

class HomePageAdapter(val movieList : ArrayList<Movie>) : RecyclerView.Adapter<HomePageAdapter.VH>() {
    class VH(val binding : RecyclerRowHomepageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageAdapter.VH {
        val binding = RecyclerRowHomepageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: HomePageAdapter.VH, position: Int) {
        holder.binding.movieName.text = movieList[position].title
        holder.binding.imageView.downloadFromUrl(movieList[position].poster_path, null)
        holder.binding.movieItemLinear.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToMovieDetailFragment(movieId = movieList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateData(newList : List<Movie>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}