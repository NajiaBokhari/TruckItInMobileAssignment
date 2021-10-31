package com.truckitin.truckitinmobileassignment.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.truckitin.truckitinmobileassignment.NIGHT_MODE_KEY
import com.truckitin.truckitinmobileassignment.NIGHT_MODE_ON
import com.truckitin.truckitinmobileassignment.R

class ThemeSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val nightModePreferenceList: ListPreference? = findPreference(NIGHT_MODE_KEY)

        nightModePreferenceList?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue == NIGHT_MODE_ON) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            true
        }
    }
}