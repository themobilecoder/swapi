package com.rafaelds.swapi.data

interface DtoToModelMapper <Dto, Model> {
    fun convert(dto: Dto) : Model
}