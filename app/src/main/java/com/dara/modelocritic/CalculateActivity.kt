package com.dara.modelocritic

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TableLayout
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
        val datos = intent.getSerializableExtra("DATOS") as Array<List<Any>>

        val tableLayout = binding.tableLayout

        // Agregar fila de encabezado con nombres de criterios
        val headerRow = createHeaderRow(criterios)
        tableLayout.addView(headerRow)

        // Agregar filas para cada alternativa y sus datos
        for ((index, alternativa) in alternativas.withIndex()) {
            val dataRow = createDataRow(alternativa, criterios, datos[index])
            tableLayout.addView(dataRow)
        }
    }

    private fun createHeaderRow(criterios: List<Criterio>): TableRow {
        val headerRow = TableRow(this)
        headerRow.setBackgroundResource(R.drawable.table_border)

        val emptyHeaderCell = createCell("")
        headerRow.addView(emptyHeaderCell)

        for (criterio in criterios) {
            val headerCell = criterio.nombre?.let { createCell(it) }
            headerRow.addView(headerCell)
        }

        return headerRow
    }

    private fun createDataRow(alternativa: Alternativa, criterios: List<Criterio>, data: List<Any>): TableRow {
        val dataRow = TableRow(this)
        dataRow.setBackgroundResource(R.drawable.table_border)

        val alternativaCell = alternativa.nombre?.let { createCell(it) }
        dataRow.addView(alternativaCell)

        for (value in data) {
            val dataCell = createCell(value.toString())
            dataRow.addView(dataCell)
        }

        return dataRow
    }

    private fun createCell(text: String): TextView {
        val cell = TextView(this)
        cell.text = text
        cell.setBackgroundResource(R.drawable.table_border)
        cell.setPadding(16, 16, 16, 16)
        cell.setTextColor(resources.getColor(R.color.purple_200)) // Cambia al color de texto deseado
        return cell
    }
}