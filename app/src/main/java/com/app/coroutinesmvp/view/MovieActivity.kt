package com.app.coroutinesmvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.coroutinesmvp.MovieContract
import com.app.coroutinesmvp.databinding.ActivityMainBinding
import com.app.coroutinesmvp.deps.DaggerAppComponent
import com.app.coroutinesmvp.presenter.MoviePresenter
import javax.inject.Inject

class MovieActivity : AppCompatActivity(), MovieContract.MovieView {

    @Inject
    lateinit var activityMainBinding: ActivityMainBinding

    @Inject
    lateinit var moviePresenter: MoviePresenter
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
}
