package com.adevinta.mappicker.classes.entidades

data class UsuarioToken (
    val token: String,
    val userId: Long,
    val nome: String
){
}