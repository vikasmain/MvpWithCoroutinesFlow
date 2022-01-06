package com.app.coroutinesmvp.deps

import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent : ActivityDepsProvider {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}
