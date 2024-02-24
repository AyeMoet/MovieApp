package com.am.themovieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ViewItemBannerBinding
import com.am.themovieapp.delegates.BannerViewHolderDelegate
import com.am.themovieapp.viewholders.BannerViewHolder

class BannerAdapter(val mDelegate: BannerViewHolderDelegate): RecyclerView.Adapter<BannerViewHolder>() {

    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = ViewItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannerViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (mMovieList.size > 5) {
            5
        } else {
            mMovieList.size
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}