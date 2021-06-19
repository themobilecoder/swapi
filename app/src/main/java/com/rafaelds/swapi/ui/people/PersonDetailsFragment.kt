package com.rafaelds.swapi.ui.people

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.model.ViewState.State.*
import com.rafaelds.swapi.databinding.FragmentPersonDetailsBinding
import com.rafaelds.swapi.ui.ExtensionUtil.safeCapitalize
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalPagingApi
@AndroidEntryPoint
class PersonDetailsFragment : Fragment() {

    private var _binding: FragmentPersonDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonDetailViewModel by viewModels()
    private val args: PersonDetailsFragmentArgs by navArgs()

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
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state.state) {
                SUCCESS -> {
                    binding.container.visibility = View.VISIBLE
                    binding.offline.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.GONE
                    binding.refreshLayout.isRefreshing = false
                    state.data?.let { person ->
                        binding.name.text = person.name.safeCapitalize()
                        binding.eyeColor.text = person.eyeColor.safeCapitalize()
                        binding.hairColor.text = person.hairColor.safeCapitalize()
                        binding.skinColor.text = person.skinColor.safeCapitalize()
                        val homeData = person.homeData
                        homeData?.let { _ ->
                            binding.homeworld.text = homeData.name
                            binding.homeworld.setOnClickListener {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(homeData.url))
                                startActivity(intent)
                            }
                        }

                        binding.mass.text = person.mass
                        binding.height.text = person.height
                        binding.gender.text = person.gender.safeCapitalize()
                    }
                }
                LOADING -> {
                    binding.offline.visibility = View.GONE
                    binding.loadingSpinner.visibility = View.VISIBLE
                }
                ERROR -> {
                    binding.container.visibility = View.GONE
                    binding.offline.visibility = View.VISIBLE
                    binding.loadingSpinner.visibility = View.GONE
                    binding.refreshLayout.isRefreshing = false
                }
                IDLE -> {
                    binding.container.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPersonDetails(args.id)
    }

}