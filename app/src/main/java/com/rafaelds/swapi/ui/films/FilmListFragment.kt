package com.rafaelds.swapi.ui.films

import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.R
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.ui.BaseListFragment
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class FilmListFragment : BaseListFragment<Film, FilmListViewModel, FilmListAdapter>() {

    override val viewModel: FilmListViewModel by viewModels()

    override fun createAdapter(): FilmListAdapter {
        return FilmListAdapter { planetUri ->
            startActivityWithLink(planetUri)
        }
    }

    override val toolbarTitle: String
        get() = resources.getString(R.string.films)
}