package com.am.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.themovieapp.adapter.MovieListAdapter
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    lateinit var mMovieAdapter: MovieListAdapter
    lateinit var mDelegate: MovieViewHolderDelegate
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

     fun setUpMovieListViewPod(delegate:MovieViewHolderDelegate) {
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate) {
        this.mDelegate = delegate

    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieListAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    fun setData(it: List<MovieVO>) {
        mMovieAdapter.setNewData(it)
    }
}