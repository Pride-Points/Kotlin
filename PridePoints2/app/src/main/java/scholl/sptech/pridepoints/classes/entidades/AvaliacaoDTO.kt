package scholl.sptech.pridepoints.classes.entidades

import java.util.Date

data class AvaliacaoDTO(
    var id: Long? = null,
    var nota: Double = 0.0,
    var dtAvaliacao: Date? = null,
    var tag: String? = null,
    var comentario: String? = null,
    var isShared: Boolean = false,
    var nomeAvaliador: String? = null,
    var resp: String? = null,
    var title: String? = null,
) {

}
