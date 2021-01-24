package com.example.covid19.help

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covid19.data.DataSource
import java.lang.IllegalArgumentException

class ContactsListViewModel(val dataSource: DataSource) : ViewModel() {

    val contactsLiveData = dataSource.getContactsList()

    val hospitalsLiveData = dataSource.getHospitalsList()

}

class ContactsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsListViewModel::class.java)) {
            return ContactsListViewModel(
                    dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}