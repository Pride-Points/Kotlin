package scholl.sptech.pridepoints.avaliacoes

import java.io.Serializable

data class Avaliacao (
    val id:Int,
    val loja:String? = "",
    val comentario:String? = "",
    val sentimento:String? = "",
    //As estrelas precisam ser a nota que o usuário deu para tal estabelecimento
    val estrelas:Int? = 0
): Serializable