package com.example.covid19.data

import android.content.res.Resources
import com.example.covid19.R

/**
 * Initial Data for contacts.
 */

fun contactsList(resources: Resources): List<ContactsModel> {
    return listOf(
            ContactsModel(
                    title = resources.getString(R.string.dha),
                    number = resources.getString(R.string.dha_num),
                    location = "null"),
            ContactsModel(
                    title = resources.getString(R.string.ambulance),
                    number = resources.getString(R.string.ambulance_num),
                    location = "null"),
            ContactsModel(
                    title = resources.getString(R.string.dubai_police),
                    number = resources.getString(R.string.dubai_police_num),
                    location = "null"),
    )
}

fun hospitalsList(resources: Resources): List<ContactsModel> {
    return listOf(
            ContactsModel(
                    title = resources.getString(R.string.saudi_german_hostpital),
                    number = resources.getString(R.string.saudi_german_num),
                    location = resources.getString(R.string.saudi_location)),
            ContactsModel(
                    title = resources.getString(R.string.mediclinic_hostpital),
                    number = resources.getString(R.string.mediclinic_number),
                    location = resources.getString(R.string.mediclinic_location)),
            ContactsModel(
                    title = resources.getString(R.string.rashid_hospital),
                    number = resources.getString(R.string.rashid_h_number),
                    location = resources.getString(R.string.rashid_h_location)),
    )
}