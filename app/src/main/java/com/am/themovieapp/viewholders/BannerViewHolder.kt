package com.am.themovieapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.BuildConfig
import com.am.themovieapp.R
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ViewItemBannerBinding
import com.am.themovieapp.delegates.BannerViewHolderDelegate
import com.bumptech.glide.Glide

class BannerViewHolder(private val binding: ViewItemBannerBinding, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mMovie: MovieVO? = null
    init {
        itemView.setOnClickListener {
            mMovie?.let { movie ->
                mDelegate.onTapMovieFromBanner(movieId = movie.id)
            }
        }
    }

    fun bindData(movieVO: MovieVO) {
        mMovie = movieVO
        binding.apply {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGE_BASE_URL + movieVO.posterPath)
                .placeholder(R.drawable.placeholder_wolverine_image)
                .into(ivBannerImage)

            tvBannerMovieName.text = movieVO.title
        }
    }
}