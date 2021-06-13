package com.rafaelds.swapi.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rafaelds.swapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

}