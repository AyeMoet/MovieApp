package com.am.themovieapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.am.themovieapp.BuildConfig
import com.am.themovieapp.R
import com.am.themovieapp.data.models.MovieModelImpl.mMovieDatabase
import com.am.themovieapp.data.vos.GenreVO
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.databinding.ActivityMovieDetailsBinding
import com.am.themovieapp.mvvm.MovieDetailsViewModel
import com.am.themovieapp.viewpods.ActorListViewPod
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    //viewModel
    private lateinit var mViewModel: MovieDetailsViewModel
    private var mFavourite = false


    companion object {

        private var mMovieID = 0

        fun newIntent(context: Context, movieId: Int): Intent {
            mMovieID = movieId
            return Intent(context,MovieDetailsActivity::class.java)
        }
    }

    //view pod
    private lateinit var actorsViewPod: ActorListViewPod
    private lateinit var creatorsViewPod: ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mMovieID.let {
            setUpViewModel(mMovieID)
        }

        setUpViewPods()
        setUpListeners()
        observeLiveData()
    }

    private fun setUpViewModel(movieId: Int) {
        mViewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        mViewModel.getInitData(movieId)
    }

    private fun observeLiveData() {
        mViewModel.movieDetailLiveData?.observe(this){
            Log.d("test_data","data is it$it")
            it.let {
                bindData(it)
            }
        }
        mViewModel.castLiveData.observe(this,actorsViewPod::setData)
        mViewModel.crewMovieLiveData.observe(this,creatorsViewPod::setData)
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(movieVo: MovieVO) {
        binding.apply {
            Glide.with(this@MovieDetailsActivity)
                .load(BuildConfig.IMAGE_BASE_URL + movieVo.backdropPath)
                .placeholder(R.drawable.placeholder_wolverine_image)
                .into(ivMovieDetails)
            tvMovieName.text = movieVo.title ?: ""
            tvMovieReleaseYear.text = movieVo.releaseDate?.substring(0, 4)
            tvRating.text = movieVo.voteAverage?.toString() ?: ""
            movieVo.voteCount?.let {
                tvNumberOfVotes.text = "$it VOTES"
            }
            rbRatingMovieDetails.rating = movieVo.getRatingBaseOnFiveStar()

            //bind genres
            bindGenres(movieVo, movieVo.genres ?: listOf())

            tvOverview.text = movieVo.overview ?: ""
            tvOriginalTitle.text = movieVo.originalTitle ?: ""
            tvType.text = movieVo.getGenreAsCommaSeparatedString()
            tvProduction.text = movieVo.getProductionCountriesAsCommaSeparatedString()
            tvPremiere.text = movieVo.releaseDate ?: ""
            tvDescription.text = movieVo.overview ?: ""
        }
    }

    private fun bindGenres(movieVo: MovieVO, genres: List<GenreVO>) {
        binding.apply {
            movieVo.genres?.count()?.let {
                tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
                tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
                tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""
                if (it < 3){
                    tvThirdGenre.visibility = View.GONE
                } else if(it < 2) {
                    tvSecondGenre.visibility = View.GONE
                }
            }
        }
    }

    private fun setUpListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                super.onBackPressed()
            }

            ivSearch.setOnClickListener {
                startActivity(MovieSearchActivity.newIntent(this@MovieDetailsActivity))
            }
            ivFavourite.setOnClickListener {
                if (mFavourite) {
                    Glide.with(this@MovieDetailsActivity)
                        .load(R.drawable.ic_baseline_favorite_white_24)
                        .into(ivFavourite)
                    mFavourite = false
                } else {
                    Glide.with(this@MovieDetailsActivity)
                        .load(R.drawable.ic_baseline_favorite_border_white_24)
                        .into(ivFavourite)
                    mFavourite = true
                }
            }
        }
    }

    private fun setUpViewPods() {
        binding.apply {
            actorsViewPod = vpActor.root
            actorsViewPod.setUpActorViewPod(
                backgroundColorReference = R.color.colorPrimary,
                titleText = getString(R.string.lbl_actors),
                moreTitleText = ""
            )
            creatorsViewPod = vpCreators.root
            creatorsViewPod.setUpActorViewPod(
                backgroundColorReference = R.color.colorPrimary,
                titleText = getString(R.string.lbl_creators),
                moreTitleText = getString(R.string.lbl_more_creators)
            )
        }
    }
}