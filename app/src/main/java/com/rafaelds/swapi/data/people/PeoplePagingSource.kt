package com.rafaelds.swapi.data.people

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafaelds.swapi.data.network.NetworkConfig
import com.rafaelds.swapi.data.local.Person

@ExperimentalPagingApi
class PeoplePagingSource constructor(private val peopleRemoteService: PeopleRemoteService, private val networkConfig: NetworkConfig) : PagingSource<Int, Person>() {

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        return try {
            val pageNumber = params.key ?: 1
            val baseUri = "${networkConfig.baseUri}$PEOPLE_SUB_URI"
            val response = peopleRemoteService.fetchData("${baseUri}?page=$pageNumber")
            val prevKey = if (response.previous != null) pageNumber - 1 else null
            val nextKey = if (response.next != null) pageNumber + 1 else null
            val data = peopleRemoteService.dtoToModelConverter(response)
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val PEOPLE_SUB_URI = "people/"
    }

}