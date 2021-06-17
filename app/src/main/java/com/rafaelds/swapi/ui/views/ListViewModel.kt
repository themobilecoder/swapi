package com.rafaelds.swapi.ui.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData

@ExperimentalPagingApi
abstract class BaseListViewModel <T: Any> : ViewModel()  {
    abstract fun fetchList() : LiveData<PagingData<T>>
}