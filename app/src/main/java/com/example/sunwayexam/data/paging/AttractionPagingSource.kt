package com.example.sunwayexam.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sunwayexam.data.api.ApiService
import com.example.sunwayexam.model.attraction.Attraction
import retrofit2.HttpException
import java.io.IOException

// 初始的第一個Index
private const val STARTING_PAGE_INDEX = 1

/**
 * 旅遊景點的 PagingSource
 */
class AttractionPagingSource(
    private val apiService: ApiService,
    private val lang: String
): PagingSource<Int, Attraction>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Attraction> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiService.getAttractions(lang, position)
            val attractions = response.data
            val nextKey = if (attractions.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + 1
            }
            LoadResult.Page(
                data = attractions,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Attraction>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}