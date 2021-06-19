package com.rafaelds.swapi.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.films.FilmRepository
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.films.Film
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class FilmDetailsViewModel @Inject constructor(private val filmRepository: FilmRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Film>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Film>>(ViewState.idle())


    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val details = filmRepository.getFilmDetails(id)
                _screenState.postValue(ViewState.success(details))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}