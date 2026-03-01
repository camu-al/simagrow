package com.camu.simagrow.activitis

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.camu.simagrow.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*-------------------- BOTON INICIAR SESION  --------------------*/
        binding.btnIniciarSesion.setOnClickListener {
            val niaIntroducido = binding.etNia.text.toString().trim()
            val passwordIntroducido = binding.etPassword.text.toString().trim()

            if (niaIntroducido.isEmpty() || passwordIntroducido.isEmpty()) {
                showToast("Rellena todos los campos")
                return@setOnClickListener
            }

            val niaRegistrado = intent.getStringExtra("NIA")
            val passwordRegistrado = intent.getStringExtra("PASSWORD")

            val loginCorrecto = niaIntroducido == niaRegistrado && passwordIntroducido == passwordRegistrado

            if (loginCorrecto) {
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
                finish()
            } else {
                showToast("Los campos son incorrectos")
            }
        }

        /*-------------------- BOTON CREAR CUENTA --------------------*/
        binding.btnCrearCuenta.setOnClickListener {
            val intentRegistro = Intent(this, RegistreActivity::class.java)
            startActivity(intentRegistro)
        }
    }

    /*-------------------- FUNCIÓN TOAST --------------------*/
    private fun showToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
