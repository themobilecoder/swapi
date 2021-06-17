package com.rafaelds.swapi.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafaelds.swapi.data.model.DtoToModelMapper
import com.rafaelds.swapi.data.network.RemoteService

abstract class BasePagingSource<Value : Any, Dto> constructor(
    private val uri: String,
    private val remoteService: RemoteService<Dto>,
    private val mapper: DtoToModelMapper<Dto, List<Value>>
) : PagingSource<Int, Value>() {

    abstract fun getPreviousKey(pageNumber: Int, response: Dto): Int?
    abstract fun getNextKey(pageNumber: Int, response: Dto): Int?

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteService.fetchData("$uri?page=$pageNumber")
            val prevKey = getPreviousKey(pageNumber, response)
            val nextKey = getNextKey(pageNumber, response)
            val data = mapper.convert(response)
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}