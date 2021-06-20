package com.rafaelds.swapi.ui.species

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
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.databinding.FragmentSpecieDetailsBinding
import com.rafaelds.swapi.ui.LinksAdapter
import com.rafaelds.swapi.ui.films.FilmDetailsFragmentArgs
import com.rafaelds.swapi.ui.utils.ExtensionUtil.startActivityWithLink
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class SpecieDetailsFragment : Fragment() {
    private var _binding: FragmentSpecieDetailsBinding? = null
    private val binding get() = _binding!!

    private val specieDetailsViewModel: SpecieDetailsViewModel by viewModels()
    private val args: FilmDetailsFragmentArgs by navArgs()

    private lateinit var charactersAdapter: LinksAdapter
    private lateinit var filmsAdapter: LinksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpecieDetailsBinding.inflate(inflater, container, false)
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
            specieDetailsViewModel.fetchDetails(args.id)
        }
        charactersAdapter = LinksAdapter(openScreenOnUrl())
        filmsAdapter = LinksAdapter(openScreenOnUrl())
        setupLinksAdapter(charactersAdapter, binding.charactersList)
        setupLinksAdapter(filmsAdapter, binding.filmsList)

        specieDetailsViewModel.screenState.observe(viewLifecycleOwner) { state ->
            handleViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        specieDetailsViewModel.fetchDetails(args.id)
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

    private fun handleViewState(state: ViewState<Specie>) {
        with(binding) {
            when (state.state) {
                ViewState.State.SUCCESS -> {
                    container.visibility = View.VISIBLE
                    offline.visibility = View.GONE
                    loadingSpinner.visibility = View.GONE
                    refreshLayout.isRefreshing = false
                    state.data?.let { specie ->
                        name.text = specie.name
                        classification.text = specie.classification
                        designation.text = specie.designation
                        language.text = specie.language
                        height.text = specie.averageHeight
                        lifespan.text = specie.averageLifespan
                        eyeColors.text = specie.eyeColors
                        skinColors.text = specie.skinColors
                        hairColors.text = specie.hairColors
                        specie.homeworld?.let { home ->
                            homeworld.text = home.name
                            homeworld.setOnClickListener {
                                startActivityWithLink(home.url)
                            }
                        }

                        if (specie.people.isEmpty()) {
                            charactersSection.visibility = View.GONE
                        } else {
                            charactersSection.visibility = View.VISIBLE
                            charactersAdapter.submitList(specie.people)
                        }
                        if (specie.films.isEmpty()) {
                            filmsSection.visibility = View.GONE
                        } else {
                            filmsSection.visibility = View.VISIBLE
                            filmsAdapter.submitList(specie.films)
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