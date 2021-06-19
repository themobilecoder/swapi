package com.rafaelds.swapi.ui.vehicles

import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.ui.BaseListFragment
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class VehicleListFragment : BaseListFragment<Vehicle, VehicleListViewModel, VehicleListAdapter>() {

    override val viewModel: VehicleListViewModel by viewModels()

    override fun createAdapter(): VehicleListAdapter {
        return VehicleListAdapter { starshipUri ->
            startActivityWithLink(starshipUri)
        }
    }
}