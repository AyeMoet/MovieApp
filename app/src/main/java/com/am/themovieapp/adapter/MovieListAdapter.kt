package com.am.themovieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ViewHolderMovieBinding
import com.am.themovieapp.delegates.MovieViewHolderDelegate
import com.am.themovieapp.viewholders.MovieViewHolder

class MovieListAdapter(private val mDelegate: MovieViewHolderDelegate) : RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(view, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}