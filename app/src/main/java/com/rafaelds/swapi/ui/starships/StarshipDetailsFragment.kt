package com.rafaelds.swapi.ui.starships

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
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.databinding.FragmentStarshipDetailsBinding
import com.rafaelds.swapi.ui.LinksAdapter
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class StarshipDetailsFragment : Fragment() {
    private var _binding: FragmentStarshipDetailsBinding? = null
    private val binding get() = _binding!!

    private val starshipDetailsViewModel: StarshipDetailsViewModel by viewModels()
    private val args: StarshipDetailsFragmentArgs by navArgs()

    private lateinit var pilotsAdapter: LinksAdapter
    private lateinit var filmsAdapter: LinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStarshipDetailsBinding.inflate(inflater, container, false)
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
            starshipDetailsViewModel.fetchDetails(args.id)
        }
        pilotsAdapter = LinksAdapter(openScreenOnUrl())
        filmsAdapter = LinksAdapter(openScreenOnUrl())
        setupLinksAdapter(pilotsAdapter, binding.pilotsList)
        setupLinksAdapter(filmsAdapter, binding.filmsList)

        starshipDetailsViewModel.screenState.observe(viewLifecycleOwner) { state ->
            handleViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        starshipDetailsViewModel.fetchDetails(args.id)
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

    private fun handleViewState(state: ViewState<Starship>) {
        with(binding) {
            when (state.state) {
                ViewState.State.SUCCESS -> {
                    container.visibility = View.VISIBLE
                    offline.visibility = View.GONE
                    loadingSpinner.visibility = View.GONE
                    refreshLayout.isRefreshing = false
                    state.data?.let { starship ->
                        name.text = starship.name
                        starshipClass.text = starship.starshipClass
                        model.text = starship.model
                        manufacturer.text = starship.manufacturer
                        length.text = starship.length
                        crew.text = starship.crew
                        mglt.text = starship.MGLT
                        cargoCapacity.text = starship.cargoCapacity
                        consumables.text = starship.consumables
                        cost.text = starship.costInCredits
                        hyperdriveRating.text = starship.hyperdriveRating
                        maxAtmospheringSpeed.text = starship.maxAtmoshperingSpeed
                        passengers.text = starship.passengers

                        if (starship.pilots.isEmpty()) {
                            pilotsSection.visibility = View.GONE
                        } else {
                            pilotsSection.visibility = View.VISIBLE
                            pilotsAdapter.submitList(starship.pilots)
                        }
                        if (starship.films.isEmpty()) {
                            filmsSection.visibility = View.GONE
                        } else {
                            filmsSection.visibility = View.VISIBLE
                            filmsAdapter.submitList(starship.films)
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