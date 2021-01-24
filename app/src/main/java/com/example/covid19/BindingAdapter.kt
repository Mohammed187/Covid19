package com.example.covid19

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.help.ContactsAdapter
import com.example.covid19.data.ContactsModel

/**
 * Bind the recycler Views Data
 */
@BindingAdapter("listData")
fun bindRecyclerview(recyclerView: RecyclerView, data: List<ContactsModel>?) {
    val adapter = recyclerView.adapter as ContactsAdapter
    adapter.submitList(data)
}