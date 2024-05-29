package com.example.sunwayexam.data.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sunwayexam.data.api.ApiService
import com.example.sunwayexam.model.attraction.Attraction
import com.example.sunwayexam.utils.AttractionFactory
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class AttractionPagingSourceTest {
    private val apiService = mockk<ApiService>()
    private val lang = "zh-tw"
    private lateinit var attractionPagingSource: AttractionPagingSource
    private val attractionFactory = AttractionFactory()
    val attractions = listOf(
        attractionFactory.createFakeAttraction(),
        attractionFactory.createFakeAttraction(),
        attractionFactory.createFakeAttraction()
    )


    @Before
    fun setUp() {
        attractionPagingSource = AttractionPagingSource(apiService, lang)
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {

        coEvery {
            apiService.getAttractions(lang, 1)
        } returns attractionFactory.createApiBean(
            attractions
        )

        val expectedResult = PagingSource.LoadResult.Page(
            data = attractions,
            prevKey = null,
            nextKey = 2
        )

        val actualResult = attractionPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun loadReturnsPageWhenOnEmptyLoadOfItemKeyedData() = runTest {
        val attractions = emptyList<Attraction>()

        coEvery {
            apiService.getAttractions(lang, 1)
        } returns attractionFactory.createApiBean(
            attractions
        )

        val expectedResult = PagingSource.LoadResult.Page(
            data = attractions,
            prevKey = null,
            nextKey = null
        )

        val actualResult = attractionPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun loadReturnsErrorWhenIOExceptionThrown() = runTest {
        coEvery { apiService.getAttractions(lang, 1) } throws IOException()

        val result = attractionPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun loadReturnsErrorWhenHttpExceptionThrown() = runTest {
        // mock api response
        val response: Response<Any> = mockk {
            every { code() } returns 500
            every { message() } returns "Internal Server Error"
            every { errorBody() } returns mockk()
            every { headers() } returns mockk()
            every { isSuccessful } returns false
        }

        coEvery { apiService.getAttractions(lang, 1) } throws HttpException(response)

        val result = attractionPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun getRefreshKeyReturnsCorrectKey() {
        val pagingState = PagingState(
            pages = listOf(
                PagingSource.LoadResult.Page(
                    data = attractions,
                    prevKey = null,
                    nextKey = 2
                )
            ),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 1),
            leadingPlaceholderCount = 0
        )

        val refreshKey = attractionPagingSource.getRefreshKey(pagingState)

        assertEquals(1, refreshKey)
    }
}