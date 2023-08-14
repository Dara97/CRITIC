package com.dara.modelocritic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.dara.modelocritic.databinding.ActivityMainBinding

data class Alternativa(val nombre: String?): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Alternativa> {
        override fun createFromParcel(parcel: Parcel): Alternativa {
            return Alternativa(parcel)
        }

        override fun newArray(size: Int): Array<Alternativa?> {
            return arrayOfNulls(size)
        }
    }

}

data class Criterio(val nombre: String?, val dato: List<Int>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("dato")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Criterio> {
        override fun createFromParcel(parcel: Parcel): Criterio {
            return Criterio(parcel)
        }

        override fun newArray(size: Int): Array<Criterio?> {
            return arrayOfNulls(size)
        }
    }
}

data class InputData(
    val nombres: EditText,
    val datos: List<EditText?>? = null,
    val numero: EditText,
    val saveNames: Button,
    val saveDatos: Button? = null
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val datosAlternativas = mutableListOf<Alternativa>()
    private val datosCriterios = mutableListOf<Criterio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val datosAlternativasInput = InputData(
            nombres = binding.editTextNombresDeAlternativas,
            numero = binding.editTextNumeroDeAlternativas,
           saveNames = binding.botonGuardarAlternativa
        )

        val datosCriteriosInput = InputData(
           nombres=  binding.editTextNombresDeCriterios,
            datos = listOf(binding.editTextDatosDeCriterios),
            numero = binding.editTextNumeroDeCriterios,
           saveNames = binding.botonGuardarCriterios,
            saveDatos = binding.botonGuardarDatosMatriz
        )

        val Calcular: Button = binding.Calcular
        val botonGuardarAlternativa: Button = binding.botonGuardarAlternativa
        val botonGuardarCriterios: Button = binding.botonGuardarCriterios
        val crear: Button = binding.buttonAgregarDatos

        binding.botonGuardarAlternativa.isEnabled = false
        binding.botonGuardarCriterios.isEnabled = false

//        agregarDatos(
//            datosAlternativas,
//            datosCriterios,
//            botonGuardarAlternativa,
//            botonGuardarCriterios,
//            crear,
//            binding.editTextNumeroDeCriterios
//        )

        crear.setOnClickListener {
            if (binding.editTextNumeroDeAlternativas.text.trim().toString().isNotEmpty() && binding.editTextNumeroDeCriterios.text.trim().toString().isNotEmpty()) {
                agregarDatos(
                    datosAlternativas,
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
        Calcular.setOnClickListener {
            val intent = Intent(this, CalculateActivity::class.java)
            intent.putParcelableArrayListExtra("ALTERNATIVAS", ArrayList(datosAlternativas))
            intent.putParcelableArrayListExtra("CRITERIOS", ArrayList(datosCriterios))
            startActivity(intent)
        }

        binding.botonGuardarAlternativa.setOnClickListener {
            val listaAlternativasActual = binding.ListaAlternativas
            val listaAlternativas = binding.editTextNombresDeAlternativas.text.toString()
            if (binding.editTextNombresDeAlternativas.text.trim().toString().isNotEmpty()) {
                guardarDatosAlternativas(
                    datosAlternativas,
                    datosAlternativasInput
                )
                mostrarMensajeEnPantalla(listaAlternativas, listaAlternativasActual)
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.botonGuardarCriterios.setOnClickListener {
            if (binding.editTextNombresDeCriterios.text.toString().trim().isNotEmpty()
            ) {
                guardarDatosCriterios(
                    datosCriterios,
                    datosCriteriosInput
                )
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre válido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}


fun agregarDatos(
    datosAlternativas: MutableList<Alternativa>,
    datosCriterios: MutableList<Criterio>,
    botonGuardarAlternativa: Button,
    botonGuardarCriterios: Button,
    numeroDeCriterios: String,
    numeroDeAlternativas: String
) {
    if (numeroDeCriterios.toInt() > 1 && numeroDeAlternativas.toInt() > 1){
        botonGuardarAlternativa.isEnabled = true
        botonGuardarCriterios.isEnabled = true
        datosAlternativas.clear()
        datosCriterios.clear()
        Log.d("PRUEBAS", numeroDeCriterios.toString())
        Log.d("PRUEBAS", numeroDeAlternativas.toString())
    } else {
        Log.d("PRUEBAS", "Ingrese numeros validos")
    }




        // Obtener la cantidad de criterios ingresados
        val cantidadCriterios = numeroDeCriterios.toString().toIntOrNull() ?: 0
        // Crear la matriz para almacenar las relaciones entre alternativas y criterios
        val matrizRelaciones = MutableList(cantidadCriterios) { MutableList(datosAlternativas.size) { 0 } }
        // Llenar la matriz con los datos ingresados por el usuario (en este caso, 0 por defecto)
        // Puedes reemplazar los 0 por los datos ingresados por el usuario para cada relación
        for (i in 0 until cantidadCriterios) {
            for (j in datosAlternativas.indices) {
                matrizRelaciones[i][j] = 0 // Puedes reemplazar 0 por el dato que el usuario ingresó
            }
        }
        // Imprimir la matriz para verificar los datos
        for (fila in matrizRelaciones) {
            Log.d("PRUEBAS", fila.joinToString())
        }
}

fun guardarDatosAlternativas(
    datosAlternativas: MutableList<Alternativa>,
    inputData: InputData
) {
    val cantidadNombres = inputData.numero.text.toString().toIntOrNull()
    if (cantidadNombres != null && cantidadNombres > 0) {
        val nombre = inputData.nombres.text.toString()
       // val datos = inputData.datos?.text.toString().toInt()
        datosAlternativas.add(Alternativa(nombre))
        inputData.nombres.text.clear()
        if (datosAlternativas.size >= cantidadNombres) {
            inputData.saveNames.isEnabled = false
        }
    } else {
        Log.d("PRUEBAS", "Ingresar un numero valido")
    }
    Log.d("PRUEBAS", "Alternativas" + datosAlternativas.toString())
}

fun guardarDatosCriterios(
    datosCriterios: MutableList<Criterio>,
    inputData: InputData
) {
    val cantidadNombres = inputData.numero.text.toString().toIntOrNull()
    if (cantidadNombres != null && cantidadNombres > 0) {
        val nombre = inputData.nombres.text.toString()
        val datos = inputData.datos?.map { it?.text?.toString()?.toIntOrNull() ?: 0 } ?: emptyList()
        datosCriterios.add(Criterio(nombre, datos))
        inputData.nombres.text.clear()
        inputData.datos?.forEach { it?.text?.clear() }

        if (datosCriterios.size >= cantidadNombres) {
            inputData.saveNames.isEnabled = false
        }
    } else {
        Log.d("PRUEBAS", "Ingresar un numero valido")
    }
    Log.d("PRUEBAS", "Criterios" + datosCriterios.toString())
}

private fun mostrarMensajeEnPantalla(listaAlternativa: String, listaAlternativasActual:TextView ) {
    val mensajeActual = listaAlternativasActual.text.toString()
    listaAlternativasActual.text = mensajeActual + "\n" + listaAlternativa
}