package com.rafaelds.swapi.data

data class DataState<T>(val state: State, val data: T?) {
    companion object {
        fun <T> success(data: T): DataState<T> {
            return DataState(State.SUCCESS, data)
        }
        fun <T> loading(): DataState<T> {
            return DataState(State.LOADING, null)
        }
        fun <T> error(): DataState<T> {
            return DataState(State.ERROR, null)
        }
    }
}