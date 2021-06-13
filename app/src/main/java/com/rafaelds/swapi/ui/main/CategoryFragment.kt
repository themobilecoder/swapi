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
class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_category, container, false)
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
                    CategoryFragmentDirections.actionMainDestToPeopleListFragment()
                )
            },
            Category(resources.getString(R.string.planets)) {
                findNavController().navigate(
                    CategoryFragmentDirections.actionMainDestToPlanetListFragment()
                )
            },
            Category(resources.getString(R.string.films)) {
                findNavController().navigate(
                    CategoryFragmentDirections.actionMainDestToFilmListDest()
                )
            },
            Category(resources.getString(R.string.species)) {
                findNavController().navigate(
                    CategoryFragmentDirections.actionMainDestToSpecieListDest()
                )
            },
            Category(resources.getString(R.string.starships)) {
                findNavController().navigate(
                    CategoryFragmentDirections.actionMainDestToStarshipListDest()
                )
            },
        )
    )


}