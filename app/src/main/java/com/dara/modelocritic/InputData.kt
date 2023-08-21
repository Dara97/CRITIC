package com.dara.modelocritic

import android.widget.Button
import android.widget.EditText

data class InputData(
    val nombres: EditText? = null,
    val datos: EditText? = null,
    val numero: EditText? = null,
    val saveNames: Button? = null,
    val saveDatos: Button? = null
)
