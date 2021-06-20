package com.rafaelds.swapi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelds.swapi.R
import com.rafaelds.swapi.databinding.FragmentCategoryBinding
import com.rafaelds.swapi.ui.utils.NavigationUtils.navigateSafe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    lateinit var categoriesAdapter: CategoriesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.listViewCategories
        categoriesAdapter = CategoriesAdapter()
        with(recyclerView) {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        categoriesAdapter.submitList(getCategories())
    }

    private fun getCategories() = listOf(
        Category(resources.getString(R.string.films)) {
            findNavController().navigateSafe(
                CategoryFragmentDirections.actionMainDestToFilmListDest()
            )
        },
        Category(resources.getString(R.string.people)) {
            findNavController().navigateSafe(
                CategoryFragmentDirections.actionMainDestToPersonListFragment()
            )
        },
        Category(resources.getString(R.string.planets)) {
            findNavController().navigateSafe(
                CategoryFragmentDirections.actionMainDestToPlanetListFragment()
            )
        },
        Category(resources.getString(R.string.species)) {
            findNavController().navigateSafe(
                CategoryFragmentDirections.actionMainDestToSpecieListDest()
            )
        },
        Category(resources.getString(R.string.starships)) {
            findNavController().navigateSafe(
                CategoryFragmentDirections.actionMainDestToStarshipListDest()
            )
        },
        Category(resources.getString(R.string.vehicles)) {
            findNavController().navigateSafe(
                CategoryFragmentDirections.actionMainDestToVehicleListDest()
            )
        },
    )


}