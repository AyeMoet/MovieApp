package com.am.themovieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.BuildConfig
import com.am.themovieapp.R
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ViewHolderShowcasesBinding
import com.am.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.bumptech.glide.Glide

class ShowcaseViewHolder(private val binding: ViewHolderShowcasesBinding, private val mDelegate: ShowCaseViewHolderDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
                mDelegate.onTapMovieFromShowcase(movieId = movie.id)
            }
        }
    }

    fun bindData(movieVO: MovieVO) {
        mMovieVO = movieVO
        binding.apply {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_BASE_URL + movieVO.posterPath)
                .placeholder(R.drawable.placeholder_wolverine_image)
                .into(ivShowcase)

            tvShowcaseMovieName.text = movieVO.title
            tvShowcaseMovieDate.text = movieVO.releaseDate
        }
    }
}