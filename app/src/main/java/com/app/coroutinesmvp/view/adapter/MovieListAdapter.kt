package com.app.coroutinesmvp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.app.coroutinesmvp.MovieStateFlow
import com.app.coroutinesmvp.data.MovieListResponse
import com.app.coroutinesmvp.databinding.MovieItemBinding
import com.app.coroutinesmvp.deps.BASEURL
import com.bumptech.glide.Glide

class MovieListAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieItemsViewHolder>() {
    private val moviesList = mutableListOf<MovieListResponse.MovieListResult>()
    internal fun updateMovieItemsList(itemsResponse: List<MovieListResponse.MovieListResult>) {
        moviesList.clear()
        moviesList.addAll(itemsResponse)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemsViewHolder {
        val binding = MovieItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemsViewHolder, position: Int) {
        holder.bindDetails(moviesList[position], context)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}

class MovieItemsViewHolder(@NonNull val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindDetails(
        movieListResponse: MovieListResponse.MovieListResult,
        context: Context
    ) {
        with(binding) {
            title.text = movieListResponse.title
            description.text = movieListResponse.overview
            "Rating: ${movieListResponse.voteAverage}".also { rating.text = it }
            itemView.setOnClickListener {
                MovieStateFlow.onClickStateFlow.value = movieListResponse.title
            }
        }
    }
}
