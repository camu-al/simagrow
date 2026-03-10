package com.camu.simagrow.activitis

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        // Lenguaje pordefecto español
        val lang = prefs.getString("idioma", "es") ?: "es"

        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(lang)
        )
    }
}
