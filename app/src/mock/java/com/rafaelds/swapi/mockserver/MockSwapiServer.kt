package com.rafaelds.swapi.mockserver

import android.content.Context
import android.util.Log
import com.rafaelds.swapi.controllers.MasterController
import fi.iki.elonen.NanoHTTPD

class MockSwapiServer(context: Context) : NanoHTTPD(8000) {

    private val masterController = MasterController(context)

    fun startServer() {
        try {
            start()
        } catch (e: Exception) {
            Log.e("MOCK", e.message ?: e.toString())
        }
    }

    override fun serve(request: IHTTPSession?): Response {
        return try {
            Log.e("MOCK", "serving ${request!!.uri}")
            val response = masterController.handleUrl(request.uri ?: "")
            if (response != null) {
                newFixedLengthResponse(Response.Status.OK, "application/json", response)
            } else {
                newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "application/json", response)
            }
        } catch (e: Exception) {
            Log.e("MOCK", "Error ${e.message}")
            newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "application/json", "")

        }

    }
}