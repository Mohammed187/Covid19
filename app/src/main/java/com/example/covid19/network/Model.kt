package com.example.covid19.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val response: List<CountryData>?
) : Parcelable

@Parcelize
data class Properties(
    val confirmed: Int,
    val critical: Int,
    val deaths: Int,
    val lastChange: String,
    val lastUpdate: String,
    val recovered: Int
) : Parcelable

@Parcelize
data class CountryData(
    val continent: String,
    val country: String,
    val population: Int,
    val cases: Cases,
    val deaths: Deaths,
    val tests: Tests,
    val day: String,
    val time: String
) : Parcelable

@Parcelize
data class Cases(
    val new: String,
    val active: Int,
    val recovered: Int,
    val total: Int
) : Parcelable

@Parcelize
data class Deaths(
    val new: String,
    val total: Int
) : Parcelable

@Parcelize
data class Tests(
    val total: Int
) : Parcelable


