package com.example.covid19.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val BASE_URL = "https://covid-19-data.p.rapidapi.com/"
private const val COUNTRY_URL = "https://covid-193.p.rapidapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val retrofit2 = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(COUNTRY_URL)
    .build()

interface CovidApiService {
    @GET("totals?")
    suspend fun getTotalData(
        @Query("format") json: String = "json",
        @Header("x-rapidapi-key") api: String? = "ac29defd77msh50fc0c37c97bf87p193347jsnc640cd7b1ace"
    ): List<Properties>
}

interface CountryApiData {
    @GET("statistics?")
    suspend fun getCountryData(
        @Query("country") country: String = "UAE",
        @Header("x-rapidapi-key") api: String? = "ac29defd77msh50fc0c37c97bf87p193347jsnc640cd7b1ace"
    ): Model
}

object CovidApi {
    val retrofitService: CovidApiService by lazy {
        retrofit.create(CovidApiService::class.java)
    }

    val retrofitCountry: CountryApiData by lazy {
        retrofit2.create(CountryApiData::class.java)
    }
}