package com.rafaelds.swapi.ui.people

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.ViewModelTest
import com.rafaelds.swapi.data.DataState
import com.rafaelds.swapi.data.PeopleRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PeopleListViewModelTest : ViewModelTest() {

    private val repository: PeopleRepository = mock()

    private val viewModel = PeopleListViewModel(repository)

    @Test
    fun `should have loading as default screen state`() {
        runBlocking {
            assertEquals(DataState.loading<List<People>>(), viewModel.screenState.value)
        }
    }

    @Test
    fun `should update screen state when fetching people list`() {
        runBlocking {
            val observer: Observer<DataState<List<People>>> = mock()
            viewModel.screenState.observeForever(observer)
            val successState = DataState.success(listOf(People(1, "Random")))
            whenever(repository.getPeopleList()).thenReturn(successState)

            viewModel.fetchPeopleList()

            verify(observer).onChanged(DataState.loading())
            verify(observer).onChanged(successState)
        }
    }
}