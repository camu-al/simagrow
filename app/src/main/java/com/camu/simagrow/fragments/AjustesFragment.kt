package com.camu.simagrow.fragments

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.camu.simagrow.R
import com.camu.simagrow.activitis.MainActivity
import java.util.Locale
import androidx.core.content.edit

class AjustesFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        //Modo Oscuro
        val oscuro = findPreference<SwitchPreferenceCompat>("oscuro")

        oscuro?.setOnPreferenceChangeListener { _, newValue ->

            AppCompatDelegate.setDefaultNightMode(
                if (newValue as Boolean)
                    AppCompatDelegate.MODE_NIGHT_YES
                else
                    AppCompatDelegate.MODE_NIGHT_NO
            )

            // Recreacion de la activity
            requireActivity().recreate()
            true
        }// Fin modo oscuro

        // Musica
        val musica = findPreference<SwitchPreferenceCompat>("musica")

        musica?.setOnPreferenceChangeListener { _, newValue ->

            if (newValue as Boolean) {
                (activity as? MainActivity)?.iniciarMusica()
            } else {
                (activity as? MainActivity)?.pararMusica()
            }

            true
        }

        // Lenguaje
        findPreference<ListPreference>("idioma")?.setOnPreferenceChangeListener { _, newValue ->
            val lang = newValue.toString()

            // Guardar idioma
            requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE).edit { putString("idioma", lang) }

            // Aplicar idioma global
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(lang)
            )

            requireActivity().recreate()
            true
        }



    }
}
