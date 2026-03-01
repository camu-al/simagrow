package com.camu.simagrow.activitis

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.camu.simagrow.R
import com.camu.simagrow.databinding.ActivityRegistreBinding
import com.camu.simagrow.pojo.Alumno

class RegistreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistreBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/


        /* =================================
        *      BOTON CREAR CUENTA
        ====================================*/
        binding.btnCrearCuentaRegistro.setOnClickListener {
            val nombreCompleto = binding.etNombreRegistro.text.toString().trim()
            val nia = binding.etNiaRegistro.text.toString()
            val password = binding.etPasswordRegistro.text.toString()
            val confirmarPassword = binding.etConfirmarPasswordRegistro.text.toString()

            if (nia.isEmpty() && password.isEmpty() && confirmarPassword.isEmpty()){
                Toast.makeText(this, "Campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmarPassword){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val alumno = Alumno(nia,password, nombreCompleto)

            val irLogin = Intent(this, LoginActivity::class.java).apply {
                putExtra("NIA", alumno.nombre)
                putExtra("NIA", alumno.nia)
                putExtra("PASSWORD", alumno.password)
                putExtra("TIPO", "alumno")
            }

            startActivity(irLogin)

        }

        /* =================================
        *      BOTON INICIAR SESION
        ====================================*/

        binding.btnVolverInicioSesion.setOnClickListener {
            val irLogin = Intent(this, LoginActivity::class.java).apply {

            }
            startActivity(irLogin)
        }



    }



}