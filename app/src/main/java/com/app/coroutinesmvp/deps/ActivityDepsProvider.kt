package com.app.coroutinesmvp.deps

interface ActivityDepsProvider {
    fun providesSubComponent(): MovieComponent.Builder
}