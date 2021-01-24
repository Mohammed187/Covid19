package com.example.covid19

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.covid19.databinding.SettingsActivityBinding

private const val themeKey = "currentTheme"

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.settingsToolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    class SettingsFragment : PreferenceFragmentCompat() {

        private lateinit var sharedPref: SharedPreferences

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            sharedPref = activity?.getSharedPreferences("settings", Context.MODE_PRIVATE) ?: return
            val value = sharedPref.getInt(themeKey, 1)

            val mode: SwitchPreferenceCompat? = findPreference("dark_mode")

            mode?.setOnPreferenceClickListener {
                if (value == 1) {
                    setPrefValue(2)
                    setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    Toast.makeText(requireContext(), getString(R.string.dark_mode_enabled), Toast.LENGTH_SHORT).show()
                } else {
                    setPrefValue(1)
                    setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    Toast.makeText(requireContext(), getString(R.string.dark_mode_disabled), Toast.LENGTH_SHORT).show()
                }
                true
            }
        }

        /**
         * Set pref value for "themeKey" in sharedPreferences.
         */
        private fun setPrefValue(value: Int?) {
            with(sharedPref.edit()) {
                putInt(themeKey, value!!)
                apply()
            }
        }
    }

}