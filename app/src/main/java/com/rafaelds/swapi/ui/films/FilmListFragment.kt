package com.rafaelds.swapi.ui.films

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.ui.BaseListFragment
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class FilmListFragment : BaseListFragment<Film, FilmListViewModel, FilmListAdapter>() {

    override val viewModel: FilmListViewModel by viewModels()

    override fun createAdapter(): FilmListAdapter {
        return FilmListAdapter { planetUri ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(planetUri))
            startActivity(intent)
        }
    }

}