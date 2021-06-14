package com.rafaelds.swapi.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.data.State
import com.rafaelds.swapi.databinding.FragmentPersonListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonListFragment : Fragment() {

    private val viewModel: PersonListViewModel by viewModels()

    private var _binding: FragmentPersonListBinding? = null
    private val binding get() = _binding!!

    private lateinit var personListAdapter: PersonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val refreshLayout = binding.refreshLayout
        val loadingSpinner = binding.loadingSpinner
        val recyclerView = binding.recyclerView
        val errorView = binding.errorView

        setupAdapter(recyclerView)

        refreshLayout.setOnRefreshListener {
            viewModel.fetchPeopleList()
        }

        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state.state) {
                State.SUCCESS -> {
                    recyclerView.visibility = VISIBLE
                    errorView.visibility = GONE
                    loadingSpinner.visibility = GONE
                    refreshLayout.isRefreshing = false
                    personListAdapter.submitList(state.data)
                }
                State.LOADING -> {
                    recyclerView.visibility = GONE
                    loadingSpinner.visibility = VISIBLE
                }
                State.ERROR -> {
                    recyclerView.visibility = GONE
                    errorView.visibility = VISIBLE
                    loadingSpinner.visibility = GONE
                    refreshLayout.isRefreshing = false
                }
                State.IDLE -> {

                }
            }
        }

        viewModel.fetchPeopleList()
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        personListAdapter = PersonListAdapter()
        with(recyclerView) {
            adapter = personListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

}