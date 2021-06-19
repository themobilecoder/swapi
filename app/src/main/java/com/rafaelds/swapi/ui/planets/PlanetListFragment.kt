package com.rafaelds.swapi.ui.planets

import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.ui.BaseListFragment
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class PlanetListFragment : BaseListFragment<Planet, PlanetListViewModel, PlanetListAdapter>() {
    
    override val viewModel: PlanetListViewModel by viewModels()

    override fun createAdapter(): PlanetListAdapter {
        return PlanetListAdapter { planetUri ->
            startActivityWithLink(planetUri)
        }
    }

}