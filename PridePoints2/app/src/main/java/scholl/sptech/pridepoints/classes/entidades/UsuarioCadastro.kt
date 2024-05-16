package scholl.sptech.pridepoints.classes.entidades

data class UsuarioCadastro (
    var nome: String = "",
    var email: String = "",
    var genero: String = "",
    var orientacaoSexual: String = "",
    var cpf: String = "",
    var senha: String = ""
) {

}