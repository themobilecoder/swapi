package com.rafaelds.swapi.controllers

import android.content.Context

class MasterController(context: Context) : BaseController(context) {

    private val controllers = mutableListOf<BaseController>()

    init {
        controllers.add(PeopleController(context))
        controllers.add(PlanetsController(context))
        controllers.add(FilmsController(context))
        controllers.add(SpeciesController(context))
        controllers.add(StarshipsController(context))
        controllers.add(VehiclesController(context))
    }

    override fun handleUrl(url: String): String? {
        for(controller in controllers) {
            val body = controller.handleUrl(url)
            if (body != null) {
                return body
            }
        }
        return null
    }
}