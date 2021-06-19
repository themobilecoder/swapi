package com.rafaelds.swapi.ui.people

import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.ui.BaseListFragment
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class PersonListFragment : BaseListFragment<Person, PersonListViewModel, PersonListAdapter>() {

    override val viewModel: PersonListViewModel by viewModels()

    override fun createAdapter(): PersonListAdapter {
        return PersonListAdapter { uri ->
            startActivityWithLink(uri)
        }
    }
}