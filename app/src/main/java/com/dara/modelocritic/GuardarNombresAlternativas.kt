package com.dara.modelocritic

import android.util.Log

class GuardarNombresAlternativas {
    fun guardarNombresAlternativas(
        nombresAlternativas: MutableList<Alternativa>,
        inputData: InputData
    ) {
        val cantidadNombres = inputData.numero?.text.toString().toIntOrNull()
        if (cantidadNombres != null && cantidadNombres > 0) {
            val nombre = inputData.nombres?.text.toString()
            nombresAlternativas.add(Alternativa(nombre))
            inputData.nombres?.text?.clear()
            if (nombresAlternativas.size >= cantidadNombres) {
                inputData.saveNames?.isEnabled  = false
            }
        } else {
            Log.d("PRUEBAS", "Ingresar un numero valido")
        }
        Log.d("PRUEBAS", nombresAlternativas.toString())
    }
}