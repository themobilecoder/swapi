package com.rafaelds.swapi.data.model

import com.rafaelds.swapi.data.model.ViewState.State.*

data class ViewState<T>(val state: State, val data: T?) {

    enum class State {
        SUCCESS,
        LOADING,
        ERROR,
        IDLE
    }

    companion object {
        fun <T> success(data: T): ViewState<T> {
            return ViewState(SUCCESS, data)
        }

        fun <T> loading(): ViewState<T> {
            return ViewState(LOADING, null)
        }

        fun <T> error(): ViewState<T> {
            return ViewState(ERROR, null)
        }

        fun <T> idle(): ViewState<T> {
            return ViewState(IDLE, null)
        }
    }
}