package com.app.coroutinesmvp

import com.app.coroutinesmvp.api.MovieApi
import com.app.coroutinesmvp.model.MovieModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class MovieModelTest {
    private val api = mock<MovieApi>()
    private val model = MovieModel(api)

    @Test
    fun `test Api Succeeds`() = runBlocking {
        whenever(api.getMoviesList("a427cfb730b4a73e67c646d8b44dd216")).thenReturn(TestMovieData.getMovieData())
        model.callMoviesApi().collect {
            assertEquals(TestMovieData.getMovieData(), it)
        }
    }

    @DisplayName("if api failed then return error")
    @Test
    fun testApiFailed() = runBlocking {
        val error = NullPointerException("null pointer")
        whenever(api.getMoviesList("a427cfb730b4a73e67c646d8b44dd216")).thenThrow(error)
        model.callMoviesApi().catch {
            assertEquals("null pointer", it.message)
        }.collect { assertNull(it) }
    }
}
