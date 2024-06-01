package com.adevinta.mappicker.classes.entidades

data class EmpresaFullDTO(
    val id: Long,
    val nomeFantasia: String,
    val cnpj: String,
    val cep: String,
    val numero: Int,
    val cidade: String,
    val estado: String,
    val dono: String
)
