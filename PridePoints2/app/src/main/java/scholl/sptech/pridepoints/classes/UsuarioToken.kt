package scholl.sptech.pridepoints.classes

data class UsuarioToken (
    val token: String,
    val userId: Long,
    val nome: String
){
}