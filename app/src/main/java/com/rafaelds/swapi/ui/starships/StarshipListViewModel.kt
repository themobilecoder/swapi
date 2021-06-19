package com.rafaelds.swapi.ui.starships

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rafaelds.swapi.data.api.starships.StarshipsRepository
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.ui.views.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class StarshipListViewModel @Inject constructor(private val starshipsRepository: StarshipsRepository) :
    BaseListViewModel<Starship>() {

    override fun fetchList(): LiveData<PagingData<Starship>> {
        return starshipsRepository.getStarships().liveData.cachedIn(viewModelScope)
    }

}