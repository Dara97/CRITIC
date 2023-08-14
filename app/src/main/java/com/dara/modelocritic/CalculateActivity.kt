package com.dara.modelocritic

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dara.modelocritic.databinding.ActivityCalculateBinding

class CalculateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alternativas = intent.getParcelableArrayListExtra<Alternativa>("ALTERNATIVAS") as List<Alternativa>
        val criterios = intent.getParcelableArrayListExtra<Criterio>("CRITERIOS") as List<Criterio>

       // binding.mostrarDatos.text = "Datos de Alternativas:\n${mostrarDatos(alternativas)}\n\nDatos de Criterios:\n${mostrarDatos(criterios)}"
        Log.d("PRUEBAS", alternativas.toString())

        mostrarDatosEnMatriz(alternativas, criterios)
    }

    private fun mostrarDatos(lista: List<Any>) : String {
        var result = ""
        lista.forEachIndexed { index, data ->
            when (data) {
                is Alternativa -> result += "Alternativa $index: Nombre = ${data.nombre}\n"
                is Criterio -> result += "Criterio $index: Nombre = ${data.nombre}, Dato = ${data.dato}\n"
            }
        }
        return result
    }

    private fun mostrarDatosEnMatriz(alternativas: List<Alternativa>, criterios: List<Criterio>) {
        val gridLayout = binding.gridLayout

        // Calcular el número de filas y columnas
        val numRows = alternativas.size
        val numCols = criterios.size

        // Agregar las alternativas como filas
        for ((index, alternativa) in alternativas.withIndex()) {
            val rowView = TextView(this)
            rowView.text = "Alternativa $index: ${alternativa.nombre}"
            val params = GridLayout.LayoutParams()
            params.rowSpec = GridLayout.spec(index)
            params.columnSpec = GridLayout.spec(0)
            rowView.layoutParams = params
            gridLayout.addView(rowView)
        }

        // Agregar los criterios como columnas
        for ((index, criterio) in criterios.withIndex()) {
            val colView = TextView(this)
            colView.text = "Criterio $index: ${criterio.nombre} (${criterio.dato})"
            val params = GridLayout.LayoutParams()
            params.rowSpec = GridLayout.spec(0)
            params.columnSpec = GridLayout.spec(index + 1)
            colView.layoutParams = params
            gridLayout.addView(colView)
        }
    }
}