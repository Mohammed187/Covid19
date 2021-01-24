package com.example.covid19.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.covid19.R
import com.example.covid19.databinding.HelpActivityBinding

class HelpActivity : AppCompatActivity() {

    private val mViewModel: ContactsListViewModel by lazy {
        ViewModelProvider(
                this,
                ContactsListViewModelFactory(this)
        ).get(ContactsListViewModel::class.java)
    }

    private lateinit var binding: HelpActivityBinding

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var hospitalsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HelpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            lifecycleOwner = this@HelpActivity
            viewModel = mViewModel
        }

        contactsAdapter = ContactsAdapter(ContactListener { number, _ ->
            dialNumber(number)
        })

        hospitalsAdapter = ContactsAdapter(ContactListener { number, location ->
            showAlert(number, location)
        })

        binding.contactsRecyclerView.adapter = contactsAdapter
        binding.hospitalRecyclerView.adapter = hospitalsAdapter
    }

    /***
     * Dial a number
     */
    private fun dialNumber(number: String?) {
        val dial = Intent(Intent.ACTION_DIAL)
        dial.data = Uri.parse("tel:$number")
        startActivity(dial)
    }

    /***
     * Show location / Call Alert
     */
    private fun showAlert(number: String, location: String) {
        val builder = AlertDialog.Builder(this)
                .setTitle("Alert")
                .setPositiveButton("Call") { _, _ ->
                    dialNumber(number)
                }
                .setNegativeButton("Location") { _, _ ->
                    val gmmIntentUri =
                            Uri.parse("geo:$location")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                }
        builder.show()
    }

    /***
     * Return to the previous activity
     */
    fun close(view: View?) {
        onNavigateUp()
        Toast.makeText(
                this,
                getString(R.string.success_application),
                Toast.LENGTH_SHORT
        ).show()
    }
}