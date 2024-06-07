package com.adevinta.mappicker.classes.entidades

import java.io.Serializable

data class AvaliacaoCriacaoDTO(var id: Long? = null,
                               var nota: Double = 0.0,
                               var tag: String? = null,
                               var comentario: String? = null,
                               var isShared: Boolean = false,
                               var nomeAvaliador: String? = null,
                               var resp: String? = null,
                               var title: String? = null,
                               var idEmpresa: String? = null,
): Serializable
