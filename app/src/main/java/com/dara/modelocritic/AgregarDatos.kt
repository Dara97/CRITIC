package com.dara.modelocritic

import android.util.Log
import android.widget.Button

class AgregarDatos {

    fun agregarDatos(
        nombresAlternativas: MutableList<Alternativa>,
        nombresCriterios: MutableList<Criterio>,
        datosCriterios: MutableList<Datos>,
        botonGuardarAlternativa: Button,
        botonGuardarCriterios: Button,
        numeroDeCriterios: String,
        numeroDeAlternativas: String
    ) {
        if (numeroDeCriterios.toInt() > 1 && numeroDeAlternativas.toInt() > 1) {
            botonGuardarAlternativa.isEnabled = true
            botonGuardarCriterios.isEnabled = true
            nombresAlternativas.clear()
            nombresCriterios.clear()
            datosCriterios.clear()
            Log.d("PRUEBAS", "Criterios: $numeroDeCriterios Alternativas: $numeroDeAlternativas")
        } else {
            Log.d("PRUEBAS", "Ingrese numeros validos")
        }
    }
}
