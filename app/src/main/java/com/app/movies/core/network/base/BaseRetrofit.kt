package com.app.movies.core.network.base


import com.app.movies.core.flowretrofitcalladapter.FlowCallAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRetrofit {


    fun getRetrofitBuilder(moshi: Moshi): Retrofit.Builder = Retrofit.Builder()
        .addCallAdapterFactory(FlowCallAdapterFactory.create(moshi))
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addConverterFactory(MoshiConverterFactory.create(moshi))

}