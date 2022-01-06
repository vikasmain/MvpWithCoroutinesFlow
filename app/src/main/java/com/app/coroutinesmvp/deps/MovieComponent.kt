package com.app.coroutinesmvp.deps

import androidx.appcompat.app.AppCompatActivity
import com.app.coroutinesmvp.MovieContract
import com.app.coroutinesmvp.databinding.ActivityMainBinding
import com.app.coroutinesmvp.view.MovieActivity
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import javax.inject.Scope

@Subcomponent(modules = [MovieActivityModule::class])
@MovieActivityScope
interface MovieComponent {
    fun inject(movieActivity: MovieActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(appCompatActivity: AppCompatActivity): Builder

        @BindsInstance
        fun movieView(movieView: MovieContract.MovieView): Builder
        fun build(): MovieComponent
    }
}

@Module
class MovieActivityModule {
    companion object {

        @Provides
        @MovieActivityScope
        fun providesBinding(appCompatActivity: AppCompatActivity): ActivityMainBinding {
            return ActivityMainBinding.inflate(appCompatActivity.layoutInflater)
        }

        @Provides
        @MovieActivityScope
        fun providesCoroutineScope(): CoroutineScope{
            return MainScope() + CoroutineName("MovieCoroutine")
        }
    }
}

@Scope
@Retention(AnnotationRetention.SOURCE)
internal annotation class MovieActivityScope
