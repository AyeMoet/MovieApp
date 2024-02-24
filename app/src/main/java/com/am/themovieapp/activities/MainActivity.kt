package com.am.themovieapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.themovieapp.R
import com.am.themovieapp.adapter.BannerAdapter
import com.am.themovieapp.adapter.ShowcaseAdapter
import com.am.themovieapp.data.vos.GenreVO
import com.am.themovieapp.databinding.ActivityMainBinding
import com.am.themovieapp.delegates.BannerViewHolderDelegate
import com.am.themovieapp.delegates.MovieViewHolderDelegate
import com.am.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.am.themovieapp.mvvm.MainViewModel
import com.am.themovieapp.viewpods.ActorListViewPod
import com.am.themovieapp.viewpods.MovieListViewPod
import com.google.android.material.tabs.TabLayout


//presenter component(direct communicate with data layer)
class MainActivity : AppCompatActivity() , BannerViewHolderDelegate, ShowCaseViewHolderDelegate,
    MovieViewHolderDelegate {
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    private lateinit var binding: ActivityMainBinding

    //viewPods
    lateinit var mBestPopularMovieListviewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorListViewPod: ActorListViewPod

    //viewModel
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()

        //App Bar Leading Icon
        setUpToolbar()
        setUpViewPod()
        setUpBannerViewPager()
        setUpShowcaseRecyclerView()
        setUpListeners()

        //Observe Live Data
        observeLiveData()
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.getInitData()
    }

    private fun observeLiveData() {
        mViewModel.nowPlayingMovieLiveData?.observe(this) {
            mBannerAdapter.setNewData(it)
        }
        mViewModel.popularMovieLiveData?.observe(this,mBestPopularMovieListviewPod::setData)
        mViewModel.topRatedMovieLiveData?.observe(
            this,
            mShowcaseAdapter::setNewData)
        mViewModel.genresLiveData.observe(this, this::setUpGenreTabLayout)
        mViewModel.moviesByGenreLiveData.observe(this,mMovieByGenreViewPod::setData)
        mViewModel.actorsLiveData.observe(this,mActorListViewPod::setData)
    }

    private fun setUpViewPod() {
        binding.apply {
            mBestPopularMovieListviewPod = vpBestPopularMovieList.root
            mBestPopularMovieListviewPod.setUpMovieListViewPod(this@MainActivity)

            mMovieByGenreViewPod = vpMovieByGenre.root
            mMovieByGenreViewPod.setUpMovieListViewPod(this@MainActivity)

            mActorListViewPod = viewPodActorList.root
            mActorListViewPod.setUpActorViewPod(
                backgroundColorReference = R.color.colorPrimary,
                titleText = getString(R.string.lbl_best_actors),
                moreTitleText = getString(R.string.lbl_more_actors)
            )
        }
    }

    private fun setUpShowcaseRecyclerView() {
        binding.apply {
            mShowcaseAdapter = ShowcaseAdapter(this@MainActivity)
            rvShowcases.adapter = mShowcaseAdapter
            rvShowcases.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun setUpListeners() {
        binding.apply {
            tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    mViewModel.getMovieByGenre(tab?.position?:0)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })
        }
    }

    private fun setUpBannerViewPager() {
        binding.apply {
            mBannerAdapter = BannerAdapter(this@MainActivity)
            viewPagerBanner.adapter = mBannerAdapter

            dotsIndicatorBanner.attachTo(viewPagerBanner)
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            binding.apply {
                tabLayoutGenre.newTab().apply {
                    text = it.name
                    tabLayoutGenre.addTab(this)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_discover,menu)
        val down = menu!!.findItem(R.id.search)
        down.setOnMenuItemClickListener {
            startActivity(MovieSearchActivity.newIntent(this@MainActivity))
            false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> startActivity(MovieSearchActivity.newIntent(this@MainActivity))
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }
}