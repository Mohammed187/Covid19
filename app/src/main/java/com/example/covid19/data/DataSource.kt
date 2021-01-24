package com.example.covid19.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/***
 * Get the data from Contacts.kt
 */
class DataSource(resources: Resources) {
    private val initContacts = contactsList(resources)
    private val contactsLiveData = MutableLiveData(initContacts)

    private val initHospitalsList = hospitalsList(resources)
    private val hospitalsLiveData = MutableLiveData(initHospitalsList)

    fun getContactsList(): LiveData<List<ContactsModel>> {
        return contactsLiveData
    }

    fun getHospitalsList() : LiveData<List<ContactsModel>> {
        return hospitalsLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}