package com.rafaelds.swapi

import com.rafaelds.swapi.mockserver.MockSwapiServer

class MockSwapiServerApplication : SwapiApplication() {
    override fun onCreate() {
        super.onCreate()
        MockSwapiServer(applicationContext).startServer()
    }
}