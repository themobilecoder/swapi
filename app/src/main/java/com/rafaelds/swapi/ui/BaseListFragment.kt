package com.rafaelds.swapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafaelds.swapi.databinding.FragmentListBinding
import com.rafaelds.swapi.ui.views.BaseListViewModel

@ExperimentalPagingApi
abstract class BaseListFragment <T : Any, VM : BaseListViewModel<T>, Adapter: BaseListAdapter<T>> : Fragment() {

    abstract val viewModel: VM
    private lateinit var listAdapter: Adapter


    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    abstract fun createAdapter() : Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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
        val errorView = binding.offline

        setupAdapter(recyclerView)

        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = false
            listAdapter.refresh()
        }
        listAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                loadingSpinner.visibility = View.VISIBLE
            } else {
                loadingSpinner.visibility = View.GONE
                if (it.refresh is LoadState.Error) {
                    errorView.visibility = View.VISIBLE
                } else {
                    errorView.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchList().observe(viewLifecycleOwner) {
            listAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupAdapter(recyclerView: RecyclerView) {
        listAdapter = createAdapter()
        with(recyclerView) {
            adapter = listAdapter.withLoadStateFooter(LoadAdapter {
                listAdapter.retry()
            })
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}