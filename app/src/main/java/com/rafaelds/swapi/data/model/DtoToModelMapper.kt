package com.rafaelds.swapi.data.model

interface DtoToModelMapper <Dto, Model> {
    fun convert(dto: Dto) : Model
}