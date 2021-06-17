package com.rafaelds.swapi.ui.people

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.ui.BaseListFragment
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class PersonListFragment : BaseListFragment<Person, PersonListViewModel, PersonListAdapter>() {

    override val viewModel: PersonListViewModel by viewModels()

    override fun createAdapter(): PersonListAdapter {
        return PersonListAdapter { uri ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }
}