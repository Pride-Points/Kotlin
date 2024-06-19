package com.adevinta.mappicker.classes.entidades

import android.os.Parcel
import android.os.Parcelable

data class EmpresaFullDTO(
    val id: Long,
    val nomeFantasia: String,
    val cnpj: String,
    val cep: String,
    val numero: Int,
    val cidade: String,
    val estado: String,
    val dono: String?
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}