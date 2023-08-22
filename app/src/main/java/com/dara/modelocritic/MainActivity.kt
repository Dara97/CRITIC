package com.dara.modelocritic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.dara.modelocritic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nombresAlternativas = mutableListOf<Alternativa>()
    private val nombresCriterios = mutableListOf<Criterio>()
    private val datosCriterios = mutableListOf<Datos>()
    private val currentRow = mutableListOf<Int>()
    private val dataMatriz = mutableListOf<List<Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombresAlternativasInput = InputData(
            nombres = binding.editTextNombresDeAlternativas,
            numero = binding.editTextNumeroDeAlternativas,
           saveNames = binding.botonGuardarAlternativa
        )

        val nombresCriteriosInput = InputData(
           nombres=  binding.editTextNombresDeCriterios,
            numero = binding.editTextNumeroDeCriterios,
           saveNames = binding.botonGuardarCriterios,
        )

        val datosCriteriosInput = InputData(
            datos = binding.editTextDatosDeCriterios,
            numero = binding.editTextNumeroDeCriterios,
            saveDatos = binding.botonGuardarDatosMatriz
        )

        val Calcular: Button = binding.Calcular
        val botonGuardarAlternativa: Button = binding.botonGuardarAlternativa
        val botonGuardarCriterios: Button = binding.botonGuardarCriterios
        val crear: Button = binding.buttonAgregarDatos

        binding.botonGuardarAlternativa.isEnabled = false
        binding.botonGuardarCriterios.isEnabled = false

        crear.setOnClickListener {
            if (binding.editTextNumeroDeAlternativas.text.trim().toString().isNotEmpty() && binding.editTextNumeroDeCriterios.text.trim().toString().isNotEmpty()) {
                AgregarDatos().agregarDatos(
                    nombresAlternativas,
                    nombresCriterios,
                    datosCriterios,
                    botonGuardarAlternativa,
                    botonGuardarCriterios,
                    binding.editTextNumeroDeCriterios.text.toString(),
                    binding.editTextNumeroDeAlternativas.text.toString()
                )
            }
            else {
                Toast.makeText(this, "Por favor, ingresa un nombre válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.botonGuardarAlternativa.setOnClickListener {
            val listaAlternativasActual = binding.ListaAlternativas
            val listaAlternativas = binding.editTextNombresDeAlternativas.text.toString()
            if (binding.editTextNombresDeAlternativas.text.trim().toString().isNotEmpty()) {
                GuardarNombresAlternativas().guardarNombresAlternativas(
                    nombresAlternativas,
                    nombresAlternativasInput
                )
                mostrarMensajeEnPantalla(listaAlternativas, listaAlternativasActual)
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.botonGuardarCriterios.setOnClickListener {
            val listaCriteriosActual = binding.ListaCriterios
            val listaCriterios = binding.editTextNombresDeCriterios.text.toString()
            if (binding.editTextNombresDeCriterios.text.toString().trim().isNotEmpty()
            ) {
                GuardarNombresCriterios().guardarNombresCriterios(
                    nombresCriterios,
                    nombresCriteriosInput
                )
                mostrarMensajeEnPantalla(listaCriterios, listaCriteriosActual)
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.botonGuardarDatosMatriz.setOnClickListener {
            val listaDatosActual = binding.ListaDatos
            val listaDatos = binding.editTextDatosDeCriterios.text.toString()
            if (binding.editTextDatosDeCriterios.text.toString().trim().isNotEmpty()){
                GuardarDatosCriterios().guardarDatosCriterios(
                    currentRow,
                    datosCriteriosInput,
                    dataMatriz,
                    nombresAlternativas,
                )
                mostrarMensajeEnPantalla(listaDatos, listaDatosActual)
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        Calcular.setOnClickListener {
            val intent = Intent(this, CalculateActivity::class.java)
            intent.putParcelableArrayListExtra("ALTERNATIVAS", ArrayList(nombresAlternativas))
            intent.putParcelableArrayListExtra("CRITERIOS", ArrayList(nombresCriterios))
            intent.putExtra("DATOS", dataMatriz.map { it.toList() }.toTypedArray())
            startActivity(intent)
        }
    }
}

private fun mostrarMensajeEnPantalla(listaAlternativa: String, listaAlternativasActual:TextView ) {
    val mensajeActual = listaAlternativasActual.text.toString()
    listaAlternativasActual.text = mensajeActual + "\n" + listaAlternativa
}