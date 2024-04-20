package scholl.sptech.pridepoints.menuitens.avaliacoes

import java.io.Serializable

data class Avaliacoes (
    val loja:String? = null,
    val comentario:String? = null,
    val estrelas:Int
): Serializable