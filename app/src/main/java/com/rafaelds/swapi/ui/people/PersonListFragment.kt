package com.rafaelds.swapi.ui.people

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.FragmentPersonListBinding
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
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
            refreshLayout.isRefreshing = false
            personListAdapter.refresh()
        }
        personListAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                loadingSpinner.visibility = VISIBLE
            } else {
                loadingSpinner.visibility = GONE
                if (it.refresh is LoadState.Error) {
                    errorView.visibility = VISIBLE
                } else {
                    errorView.visibility = GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPeopleList().observe(viewLifecycleOwner) {
            personListAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        personListAdapter = PersonListAdapter { personUri ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(personUri))
            startActivity(intent)
        }
        with(recyclerView) {
            adapter = personListAdapter.withLoadStateFooter(PersonLoadAdapter {
                personListAdapter.retry()
            })
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

}