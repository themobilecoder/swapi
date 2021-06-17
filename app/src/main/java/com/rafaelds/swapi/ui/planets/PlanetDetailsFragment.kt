package com.rafaelds.swapi.ui.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.databinding.FragmentPlanetDetailsBinding
import com.rafaelds.swapi.ui.ExtensionUtil.safeCapitalize
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class PlanetDetailsFragment : Fragment() {
    private var _binding: FragmentPlanetDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlanetDetailsViewModel by viewModels()
    private val args: PlanetDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state.state) {
                ViewState.State.SUCCESS -> {
                    binding.container.visibility = View.VISIBLE
                    binding.offline.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.GONE
                    binding.refreshLayout.isRefreshing = false
                    state.data?.let {
                        binding.name.text = it.name.safeCapitalize()
                        binding.diameter.text = it.diameter
                        binding.terrain.text = it.terrain
                        binding.surfaceWater.text = it.surfaceWater
                        binding.rotationPeriod.text = it.rotationPeriod
                        binding.orbitalPeriod.text = it.orbitalPeriod
                        binding.population.text = it.population
                        binding.gravity.text = it.gravity
                    }
                }
                ViewState.State.LOADING -> {
                    binding.offline.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.VISIBLE
                }
                ViewState.State.ERROR -> {
                    binding.container.visibility = View.GONE
                    binding.offline.visibility = View.VISIBLE
                    binding.loadingSpinner.visibility = View.GONE
                    binding.refreshLayout.isRefreshing = false
                }
                ViewState.State.IDLE -> {
                    binding.container.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPlanetDetails(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}