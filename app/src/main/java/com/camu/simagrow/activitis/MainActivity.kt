package com.camu.simagrow.activitis

import MusicaPrincipal
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.camu.simagrow.R
import com.camu.simagrow.databinding.ActivityMainBinding
import com.camu.simagrow.fragments.AjustesFragment
import com.camu.simagrow.fragments.IncidenciasFragment
import com.camu.simagrow.fragments.InicioFragment
import com.camu.simagrow.fragments.PerfilFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var musicaPrincipal: MusicaPrincipal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // BINDING
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // MUSICA
        musicaPrincipal = MusicaPrincipal(this)

        /*-------------------- MENU INFERIOR (NAVEGATION) --------------------*/
        setSupportActionBar(binding.toolbarInclude.miToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // BOTONES MENU INFERIOR
        binding.bottonNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_Home -> {
                    cargarFragments(InicioFragment())
                    true
                }
                R.id.bottom_Incidencias -> {
                    cargarFragments(IncidenciasFragment())
                    true
                }
                R.id.bottom_perfil -> {
                    cargarFragments(PerfilFragment())
                    true
                }
                else -> false
            }
        }

        /*-------------------- MENU DRAWER--------------------*/
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbarInclude.miToolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        // Aplicar menu drawer
        binding.navView.setNavigationItemSelectedListener(this)


        /*-------------------- CARGAR FRAGMENTS DEFAULT --------------------*/
        if (savedInstanceState == null) {
            cargarFragments(InicioFragment())
            binding.bottonNav.selectedItemId = R.id.bottom_Home
        }

        /*-------------------- MODO OSCURO --------------------*/
        val modoOscuro: Boolean = PreferenceManager
            .getDefaultSharedPreferences(this)
            .getBoolean("oscuro", false)

        if (modoOscuro){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }

    /*-------------------- CARGAR MENU SUPERIOR --------------------*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /*-------------------- BOTONES MENU --------------------*/
    // TOOL BAR BOTONES
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionRegistrarIncidencia -> {
                showToast("Pulsado Registrar Incidencia")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // DRAWER BOTONES
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_info -> showToast("Sobre nosotros")
            R.id.nav_stats -> showToast("Estadisticas")
            R.id.nav_soporte -> showToast("Soporte")
            R.id.nav_ajustes -> {
                cargarFragments(AjustesFragment())
                PreferenceManager.getDefaultSharedPreferences(this)
            }
            R.id.nav_salir -> Toast.makeText(this, "Salir de la app", Toast.LENGTH_SHORT).show()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*-------------------- CARGAR FRAGMENTS --------------------*/
    private fun cargarFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragments, fragment)
            .commit()
    }

    /*-------------------- TOAST --------------------*/
    private fun showToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    /*-------------------- MUSICA --------------------*/
    fun iniciarMusica() {
        musicaPrincipal.reproducirSnd(R.raw.lofi_music2)
    }

    fun pararMusica() {
        musicaPrincipal.detenerMusica()
    }

    override fun onStart() {
        super.onStart()

        val musicaActivada = PreferenceManager
            .getDefaultSharedPreferences(this)
            .getBoolean("musica", true)

        if (musicaActivada) {
            musicaPrincipal.reproducirSnd(R.raw.lofi_music2)
        }
    }

    override fun onStop() {
        musicaPrincipal.detenerMusica()
        super.onStop()
    }

}