package com.dara.modelocritic

import android.os.Parcel
import android.os.Parcelable

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
