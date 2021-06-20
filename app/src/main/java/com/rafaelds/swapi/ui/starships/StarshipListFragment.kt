package com.rafaelds.swapi.ui.starships

import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.R
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.ui.BaseListFragment
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class StarshipListFragment : BaseListFragment<Starship, StarshipListViewModel, StarshipListAdapter>() {

    override val viewModel: StarshipListViewModel by viewModels()

    override fun createAdapter(): StarshipListAdapter {
        return StarshipListAdapter { starshipUri ->
            startActivityWithLink(starshipUri)
        }
    }

    override val toolbarTitle: String
        get() = resources.getString(R.string.starships)
}