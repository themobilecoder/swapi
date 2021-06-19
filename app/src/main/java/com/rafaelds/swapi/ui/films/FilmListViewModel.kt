package com.rafaelds.swapi.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rafaelds.swapi.data.api.films.FilmsRepository
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.ui.views.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class FilmListViewModel @Inject constructor(private val filmsRepository: FilmsRepository) :
    BaseListViewModel<Film>() {

    override fun fetchList(): LiveData<PagingData<Film>> {
        return filmsRepository.getFilms().liveData.cachedIn(viewModelScope)
    }

}