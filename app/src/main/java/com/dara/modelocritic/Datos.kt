package com.dara.modelocritic

import android.os.Parcel
import android.os.Parcelable

data class Datos(val datos: Int) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(datos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Datos> {
        override fun createFromParcel(parcel: Parcel): Datos {
            return Datos(parcel)
        }

        override fun newArray(size: Int): Array<Datos?> {
            return arrayOfNulls(size)
        }
    }
}
