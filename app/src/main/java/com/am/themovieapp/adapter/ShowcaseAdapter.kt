package com.am.themovieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ViewHolderShowcasesBinding
import com.am.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.am.themovieapp.viewholders.ShowcaseViewHolder

class ShowcaseAdapter(val mDelegate: ShowCaseViewHolderDelegate): RecyclerView.Adapter<ShowcaseViewHolder>() {

    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = ViewHolderShowcasesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShowcaseViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieVO: List<MovieVO>) {
        mMovieList = movieVO
        notifyDataSetChanged()
    }
}