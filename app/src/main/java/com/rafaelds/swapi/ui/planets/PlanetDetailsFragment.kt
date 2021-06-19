package com.rafaelds.swapi.ui.planets

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
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.databinding.FragmentPlanetDetailsBinding
import com.rafaelds.swapi.ui.utils.ExtensionUtil.safeCapitalize
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import com.rafaelds.swapi.ui.LinksAdapter
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class PlanetDetailsFragment : Fragment() {
    private var _binding: FragmentPlanetDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlanetDetailsViewModel by viewModels()
    private val args: PlanetDetailsFragmentArgs by navArgs()

    private lateinit var residentsAdapter: LinksAdapter
    private lateinit var filmsAdapter: LinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)
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
            viewModel.fetchPlanetDetails(args.id)
        }
        filmsAdapter = LinksAdapter(openScreenOnUrl())
        residentsAdapter = LinksAdapter(openScreenOnUrl())
        setupLinksAdapter(residentsAdapter, binding.residentsList)
        setupLinksAdapter(filmsAdapter, binding.filmList)

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            handleViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPlanetDetails(args.id)
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

    private fun handleViewState(state: ViewState<Planet>) {
        with(binding) {
            when (state.state) {
                ViewState.State.SUCCESS -> {
                    container.visibility = View.VISIBLE
                    offline.visibility = View.GONE
                    loadingSpinner.visibility = View.GONE
                    refreshLayout.isRefreshing = false
                    state.data?.let { planet ->
                        name.text = planet.name.safeCapitalize()
                        rotationPeriod.text = planet.rotationPeriod
                        orbitalPeriod.text = planet.orbitalPeriod
                        diameter.text = planet.diameter
                        gravity.text = planet.gravity
                        terrain.text = planet.terrain
                        surfaceWater.text = planet.surfaceWater
                        population.text = planet.population

                        if (planet.films.isEmpty()) {
                            filmsSection.visibility = View.GONE
                        } else {
                            filmsSection.visibility = View.VISIBLE
                            filmsAdapter.submitList(planet.films)
                        }
                        if (planet.residents.isEmpty()) {
                            residentsSection.visibility = View.GONE
                        } else {
                            residentsSection.visibility = View.VISIBLE
                            residentsAdapter.submitList(planet.residents)
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