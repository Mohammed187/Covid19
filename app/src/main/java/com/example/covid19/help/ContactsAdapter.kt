package com.example.covid19.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.data.ContactsModel
import com.example.covid19.databinding.ContactsItemBinding

class ContactsAdapter(private val clickListener: ContactListener) :
    ListAdapter<ContactsModel, ContactsAdapter.HelpViewHolder>(DiffCallback) {

    class HelpViewHolder(private var binding: ContactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contactsModel: ContactsModel?, clickListener: ContactListener) {
            binding.helpModel = contactsModel
            binding.clickListener = clickListener

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val view = ContactsItemBinding.inflate(LayoutInflater.from(parent.context))
        return HelpViewHolder(view)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        val helpModel = getItem(position)
        holder.bind(helpModel!!, clickListener)
    }
}

class ContactListener(val clickListener: (number: String, location:String) -> Unit) {
    fun onClick(contact: ContactsModel) = clickListener(contact.number, contact.location!!)
}

object DiffCallback : DiffUtil.ItemCallback<ContactsModel>() {
    override fun areItemsTheSame(oldItem: ContactsModel, newItem: ContactsModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContactsModel, newItem: ContactsModel): Boolean {
        return oldItem.number == newItem.number
    }

}
