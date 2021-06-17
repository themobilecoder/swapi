package com.rafaelds.swapi.ui.planets

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.ui.BaseListFragment
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class PlanetListFragment : BaseListFragment<Planet, PlanetListViewModel, PlanetListAdapter>() {
    
    override val viewModel: PlanetListViewModel by viewModels()

    override fun createAdapter(): PlanetListAdapter {
        return PlanetListAdapter { planetUri ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(planetUri))
            startActivity(intent)
        }
    }

}