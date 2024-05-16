package scholl.sptech.pridepoints.classes.entidades

data class UsuarioToken (
    val token: String,
    val userId: Long,
    val nome: String
){
}