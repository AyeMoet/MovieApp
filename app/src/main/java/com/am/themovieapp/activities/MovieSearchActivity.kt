package com.am.themovieapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.am.themovieapp.adapter.MovieListAdapter
import com.am.themovieapp.data.models.MovieModel
import com.am.themovieapp.data.models.MovieModelImpl
import com.am.themovieapp.databinding.ActivityMovieSearchBinding
import com.am.themovieapp.delegates.MovieViewHolderDelegate
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(),MovieViewHolderDelegate {
    private lateinit var binding: ActivityMovieSearchBinding
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context,MovieSearchActivity::class.java)
        }
    }

    // Adapter
    private lateinit var mMovieAdapter: MovieListAdapter

    ///Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpRecyclerView() {
        binding.apply {
            mMovieAdapter = MovieListAdapter(this@MovieSearchActivity)
            rvMovies.layoutManager = GridLayoutManager(this@MovieSearchActivity,2)
            rvMovies.adapter = mMovieAdapter
        }
    }

    @SuppressLint("CheckResult")
    private fun setUpListeners() {
        binding.apply {
            etSearch.textChanges() //empty string emit lote pay cause error and arrive at onError
                .debounce(500L, TimeUnit.MILLISECONDS)
                .flatMap { mMovieModel.searchMovie(it.toString())
                }//(flatMap ,switchMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    mMovieAdapter.setNewData(it)
                }, {
                    showError(it.localizedMessage ?: "")
                } )
        }
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(window.decorView, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }
}