package com.rafaelds.swapi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rafaelds.swapi.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridView = view.findViewById<GridView>(R.id.grid_view_categories)
        gridView.numColumns = 2
        gridView.adapter = setupCategoriesAdapter()
    }

    private fun setupCategoriesAdapter() = CategoriesAdapter(
        requireContext(), arrayOf(
            Category(resources.getString(R.string.people)) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainDestToPeopleListFragment()
                )
            },
            Category(resources.getString(R.string.planets)) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainDestToPlanetListFragment()
                )
            },
            Category(resources.getString(R.string.films)) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainDestToFilmListDest()
                )
            },
            Category(resources.getString(R.string.species)) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainDestToSpecieListDest()
                )
            },
            Category(resources.getString(R.string.starships)) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainDestToStarshipListDest()
                )
            },
        )
    )


}