package com.rafaelds.swapi.ui.species

import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.ui.BaseListFragment
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class SpecieListFragment : BaseListFragment<Specie, SpecieListViewModel, SpecieListAdapter>() {

    override val viewModel: SpecieListViewModel by viewModels()

    override fun createAdapter(): SpecieListAdapter {
        return SpecieListAdapter { specieUri ->
            startActivityWithLink(specieUri)
        }
    }

}