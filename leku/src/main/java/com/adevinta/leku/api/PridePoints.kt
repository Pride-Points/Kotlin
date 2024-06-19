package com.adevinta.leku.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface Apipridepoints {
        @GET("empresas/completo")
        suspend fun listarEmpresasCompleto(): List<EmpresaFullDTO>

}
data class EmpresaFullDTO(
    val id: Long,
    val nomeFantasia: String,
    val cnpj: String,
    val cep: String,
    val numero: Int,
    val cidade: String,
    val estado: String,
    val dono: String?
)