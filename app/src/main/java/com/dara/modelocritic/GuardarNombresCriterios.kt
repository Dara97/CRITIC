package com.dara.modelocritic

import android.util.Log

class GuardarNombresCriterios {
    fun guardarNombresCriterios(
        nombresCriterios: MutableList<Criterio>,
        inputData: InputData,
    ) {
        val cantidadNombres = inputData.numero?.text.toString().toIntOrNull()
        if (cantidadNombres != null && cantidadNombres > 0) {
            val nombre = inputData.nombres?.text.toString()
            nombresCriterios.add(Criterio(nombre))
            inputData.nombres?.text?.clear()
            if (nombresCriterios.size >= cantidadNombres) {
                inputData.saveNames?.isEnabled = false
            }
        } else {
            Log.d("PRUEBAS", "Ingresar un numero valido")
        }
        Log.d("PRUEBAS",nombresCriterios.toString())
    }
}
