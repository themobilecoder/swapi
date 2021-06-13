package com.rafaelds.swapi.ui.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rafaelds.swapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_specie_list, container, false)
    }

}