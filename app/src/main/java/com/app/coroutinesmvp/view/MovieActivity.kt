package com.app.coroutinesmvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coroutinesmvp.MovieContract
import com.app.coroutinesmvp.data.MovieListResponse
import com.app.coroutinesmvp.databinding.ActivityMainBinding
import com.app.coroutinesmvp.deps.DaggerAppComponent
import com.app.coroutinesmvp.presenter.MoviePresenter
import com.app.coroutinesmvp.view.adapter.MovieListAdapter
import javax.inject.Inject

class MovieActivity : AppCompatActivity(), MovieContract.MovieView {

    @Inject
    lateinit var activityMainBinding: ActivityMainBinding

    @Inject
    lateinit var moviePresenter: MoviePresenter

    private val movieItemAdapter = MovieListAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = (DaggerAppComponent.builder().build()).providesSubComponent()
            .activity(this)
            .movieView(this)
            .build()
        component.inject(this@MovieActivity)
        setContentView(activityMainBinding.root)
        moviePresenter.onAttach()
    }

    override fun showMoviesList(movieListResponse: MovieListResponse) {
        activityMainBinding.movieList.root.visibility = View.VISIBLE
        with(activityMainBinding.movieList.recyclerView) {
            adapter = movieItemAdapter
            layoutManager = LinearLayoutManager(this@MovieActivity)
            movieItemAdapter.updateMovieItemsList(movieListResponse.result)
        }
    }

    override fun showErrorView() {
        activityMainBinding.errorView.root.visibility = View.VISIBLE
    }

    override fun showLoadingView() {
        activityMainBinding.loadingView.root.visibility = View.VISIBLE
    }

    override fun hideLoadingView() {
        activityMainBinding.loadingView.root.visibility = View.GONE
    }

    override fun openSingleItemView(title: String) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
    }
}
