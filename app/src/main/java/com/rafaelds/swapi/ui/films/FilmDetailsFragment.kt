package com.rafaelds.swapi.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.databinding.FragmentFilmDetailsBinding
import com.rafaelds.swapi.ui.LinksAdapter
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class FilmDetailsFragment : Fragment() {
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val filmDetailsViewModel: FilmDetailsViewModel by viewModels()
    private val args: FilmDetailsFragmentArgs by navArgs()

    private lateinit var charactersAdapter: LinksAdapter
    private lateinit var speciesAdapter: LinksAdapter
    private lateinit var starshipsAdapter: LinksAdapter
    private lateinit var planetsAdapter: LinksAdapter
    private lateinit var vehiclesAdapter: LinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
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
            filmDetailsViewModel.fetchDetails(args.id)
        }
        charactersAdapter = LinksAdapter(openScreenOnUrl())
        planetsAdapter = LinksAdapter(openScreenOnUrl())
        starshipsAdapter = LinksAdapter(openScreenOnUrl())
        speciesAdapter = LinksAdapter(openScreenOnUrl())
        vehiclesAdapter = LinksAdapter(openScreenOnUrl())
        setupLinksAdapter(charactersAdapter, binding.charactersList)
        setupLinksAdapter(planetsAdapter, binding.planetList)
        setupLinksAdapter(starshipsAdapter, binding.starshipList)
        setupLinksAdapter(speciesAdapter, binding.specieList)
        setupLinksAdapter(vehiclesAdapter, binding.vehicleList)

        filmDetailsViewModel.screenState.observe(viewLifecycleOwner) { state ->
            handleViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        filmDetailsViewModel.fetchDetails(args.id)
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

    private fun handleViewState(state: ViewState<Film>) {
        with(binding) {
            when (state.state) {
                ViewState.State.SUCCESS -> {
                    container.visibility = View.VISIBLE
                    offline.visibility = View.GONE
                    loadingSpinner.visibility = View.GONE
                    refreshLayout.isRefreshing = false
                    state.data?.let { film ->
                        name.text = film.title
                        director.text = film.director
                        producer.text = film.producer
                        releaseDate.text = film.releaseDate
                        openingCrawl.text = film.openingCrawl

                        if (film.characters.isEmpty()) {
                            charactersSection.visibility = View.GONE
                        } else {
                            charactersSection.visibility = View.VISIBLE
                            charactersAdapter.submitList(film.characters)
                        }
                        if (film.planets.isEmpty()) {
                            planetsSection.visibility = View.GONE
                        } else {
                            planetsSection.visibility = View.VISIBLE
                            planetsAdapter.submitList(film.planets)
                        }
                        if (film.starships.isEmpty()) {
                            starshipsSection.visibility = View.GONE
                        } else {
                            starshipsSection.visibility = View.VISIBLE
                            starshipsAdapter.submitList(film.starships)
                        }
                        if (film.vehicles.isEmpty()) {
                            vehiclesSection.visibility = View.GONE
                        } else {
                            vehiclesSection.visibility = View.VISIBLE
                            vehiclesAdapter.submitList(film.vehicles)
                        }
                        if (film.species.isEmpty()) {
                            speciesSection.visibility = View.GONE
                        } else {
                            speciesSection.visibility = View.VISIBLE
                            speciesAdapter.submitList(film.species)
                        }
                    }
                }
                ViewState.State.LOADING -> {
                    offline.visibility = View.GONE
                    loadingSpinner.visibility = View.VISIBLE
                }
                ViewState.State.ERROR -> {
                    container.visibility = View.GONE
                    offline.visibility = View.VISIBLE
                    loadingSpinner.visibility = View.GONE
                    refreshLayout.isRefreshing = false
                }
                ViewState.State.IDLE -> {
                    refreshLayout.isRefreshing = false
                    container.visibility = View.GONE
                }
            }
        }

    }

}