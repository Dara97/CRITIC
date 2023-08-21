package com.dara.modelocritic

import android.util.Log

class GuardarDatosCriterios {
    fun guardarDatosCriterios(
        currentRow: MutableList<Int>,
        inputData: InputData,
        dataMatriz: MutableList<List<Any>>,
        nombresAlternativas: MutableList<Alternativa>,
    ) {
        val cantidadNombres = inputData.numero?.text.toString().toIntOrNull()
        val cantidadAlternativas = nombresAlternativas.size

        if (cantidadNombres != null && cantidadNombres > 0) {
            val dato = inputData.datos?.text.toString()
            currentRow.add(dato.toInt())
            inputData.nombres?.text?.clear()
            if (currentRow.size >= cantidadNombres) {
                dataMatriz.add(currentRow.toMutableList())
                currentRow.clear()
                if (dataMatriz.size >= cantidadAlternativas) {
                    inputData.saveDatos?.isEnabled = false
                    //dataMatriz.add(0, nombresCriterios)
                }
            }
        } else {
            Log.d("PRUEBAS", "Ingresar un numero valido")
        }
        Log.d("PRUEBAS", "Matriz " + dataMatriz.toString())
    }
}
