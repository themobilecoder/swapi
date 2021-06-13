package com.rafaelds.swapi.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rafaelds.swapi.R
import com.rafaelds.swapi.data.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleListFragment : Fragment() {

    private val viewModel: PeopleListViewModel by viewModels()

    private lateinit var peopleListAdapter: PeopleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.fragment_people_list_swipe_to_refresh)
        val loadingSpinner = view.findViewById<ProgressBar>(R.id.fragment_people_list_loading)
        val recyclerView = view.findViewById<RecyclerView>(R.id.fragment_people_list_recycler_view)
        val errorView = view.findViewById<View>(R.id.fragment_people_list_error)

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
                    peopleListAdapter.submitList(state.data)
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
            }
        }

        viewModel.fetchPeopleList()
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        peopleListAdapter = PeopleListAdapter()
        with(recyclerView) {
            adapter = peopleListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

}