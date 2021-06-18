package com.rafaelds.swapi.di

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.rafaelds.swapi.data.network.NetworkConfig
import com.rafaelds.swapi.data.network.NetworkConfigImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRequestQueue(@ApplicationContext context: Context): RequestQueue {
        return Volley.newRequestQueue(context)
    }

    @Provides
    fun provideNetworkConfig(): NetworkConfig {
        return NetworkConfigImpl()
    }
}