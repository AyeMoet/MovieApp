package com.am.themovieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.BuildConfig
import com.am.themovieapp.R
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ViewHolderMovieBinding
import com.am.themovieapp.delegates.MovieViewHolderDelegate
import com.bumptech.glide.Glide

class MovieViewHolder(private val binding: ViewHolderMovieBinding, private val mDelegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(binding.root) {
    private var mMovie: MovieVO? = null
    init {
        itemView.setOnClickListener {
            mMovie?.let { movie ->
                mDelegate.onTapMovie(movieId = movie.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovie = movie
        binding.apply {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_BASE_URL + movie.posterPath)
                .placeholder(R.drawable.placeholder_wolverine_image)
                .into(ivMovieImage)

            tvMovieName.text = movie.title
            tvMovieRating.text = movie.voteAverage?.toString()
            rbMovieRating.rating = movie.getRatingBaseOnFiveStar()
        }
    }

}