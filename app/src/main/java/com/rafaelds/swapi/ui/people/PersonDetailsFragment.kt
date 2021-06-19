package com.rafaelds.swapi.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.ViewState.State.*
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.databinding.FragmentPersonDetailsBinding
import com.rafaelds.swapi.ui.utils.ExtensionUtil.safeCapitalize
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import com.rafaelds.swapi.ui.LinksAdapter
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalPagingApi
@AndroidEntryPoint
class PersonDetailsFragment : Fragment() {

    private var _binding: FragmentPersonDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonDetailViewModel by viewModels()
    private val args: PersonDetailsFragmentArgs by navArgs()

    private lateinit var filmsAdapter: LinksAdapter
    private lateinit var starshipsAdapter: LinksAdapter
    private lateinit var vehiclesAdapter: LinksAdapter
    private lateinit var speciesAdapter: LinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
            viewModel.fetchPersonDetails(args.id)
        }
        filmsAdapter = LinksAdapter(openScreenOnUrl())
        starshipsAdapter = LinksAdapter(openScreenOnUrl())
        vehiclesAdapter = LinksAdapter(openScreenOnUrl())
        speciesAdapter = LinksAdapter(openScreenOnUrl())
        setupLinksAdapter(starshipsAdapter, binding.starshipsList)
        setupLinksAdapter(filmsAdapter, binding.filmList)
        setupLinksAdapter(vehiclesAdapter, binding.vehiclesList)
        setupLinksAdapter(speciesAdapter, binding.speciesList)


        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            handleViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPersonDetails(args.id)
    }

    private fun openScreenOnUrl() = { url: String ->
        startActivityWithLink(url)
    }

    private fun setupLinksAdapter(linksAdapter: LinksAdapter, recyclerView: RecyclerView) {
        with(recyclerView) {
            adapter = linksAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun handleViewState(state: ViewState<Person>) {
        with(binding) {
            when (state.state) {
                SUCCESS -> {
                    container.visibility = VISIBLE
                    offline.visibility = GONE
                    loadingSpinner.visibility = GONE
                    refreshLayout.isRefreshing = false
                    state.data?.let { person ->
                        name.text = person.name.safeCapitalize()
                        eyeColor.text = person.eyeColor.safeCapitalize()
                        hairColor.text = person.hairColor.safeCapitalize()
                        skinColor.text = person.skinColor.safeCapitalize()
                        person.planetData?.let { home ->
                            homeworld.text = home.name
                            homeworld.setOnClickListener {
                                startActivityWithLink(home.url)
                            }
                        }
                        mass.text = person.mass
                        height.text = person.height
                        gender.text = person.gender.safeCapitalize()
                        if (person.films.isEmpty()) {
                            filmsSection.visibility = GONE
                        } else {
                            filmsSection.visibility = VISIBLE
                            filmsAdapter.submitList(person.films)
                        }
                        if (person.starships.isEmpty()) {
                            starshipsSection.visibility = GONE
                        } else {
                            starshipsSection.visibility = VISIBLE
                            starshipsAdapter.submitList(person.starships)
                        }
                        if (person.vehicles.isEmpty()) {
                            vehiclessSection.visibility = GONE
                        } else {
                            vehiclessSection.visibility = VISIBLE
                            vehiclesAdapter.submitList(person.vehicles)
                        }
                        if (person.species.isEmpty()) {
                            speciesSection.visibility = GONE
                        } else {
                            speciesSection.visibility = VISIBLE
                            speciesAdapter.submitList(person.species)
                        }
                    }
                }
                LOADING -> {
                    offline.visibility = GONE
                    loadingSpinner.visibility = VISIBLE
                }
                ERROR -> {
                    container.visibility = GONE
                    offline.visibility = VISIBLE
                    loadingSpinner.visibility = GONE
                    refreshLayout.isRefreshing = false
                }
                IDLE -> {
                    refreshLayout.isRefreshing = false
                    container.visibility = GONE
                }
            }
        }

    }

}