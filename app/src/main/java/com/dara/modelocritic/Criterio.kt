package com.dara.modelocritic

import android.os.Parcel
import android.os.Parcelable

data class Criterio(val nombre: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
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
