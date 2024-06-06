//package scholl.sptech.pridepoints.front.telasInscricao
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.ClickableText
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.AnnotatedString
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import retrofit2.Call
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import scholl.sptech.pridepoints.ui.theme.PridePointsTheme
//import retrofit2.Callback
//import retrofit2.Response
//import scholl.sptech.pridepoints.conexao.ApiService
////import scholl.sptech.pridepoints.conexao.FisicaCriacaoDTO
//import scholl.sptech.pridepoints.R
//import scholl.sptech.pridepoints.classes.UsuarioCadastro
//import scholl.sptech.pridepoints.classes.UsuarioToken
//
//class Cadastro : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            PridePointsTheme {
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    TelaCadastro()
//                }
//            }
//        }
//    }
//}
//
//
//fun performCadastro(usuarioInfo: UsuarioCadastro, onResult: (Boolean, String) -> Unit) {
//    val retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2:8080/users") // Altere para a URL base do seu backend
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val apiService = retrofit.create(ApiService::class.java)
////    val fisicaCriacaoDTO = FisicaCriacaoDTO(
////        nome = usuarioInfo.nome,
////        email = usuarioInfo.email,
////        genero = usuarioInfo.genero,
////        orientacaoSexual = usuarioInfo.orientacaoSexual,
////        cpf = usuarioInfo.cpf,
////        senha = usuarioInfo.senha
////    )
//
//    apiService.cadastrarUsuario(fisicaCriacaoDTO).enqueue(object : Callback<UsuarioToken> {
//        override fun onResponse(call: Call<UsuarioToken>, response: Response<UsuarioToken>) {
//            if (response.isSuccessful) {
//                // Cadastro bem-sucedido
//                onResult(true, "Cadastro concluído com sucesso!")
//            } else {
//                // Erro no cadastro
//                onResult(false, "Erro no cadastro: ${response.message()}")
//            }
//        }
//
//        override fun onFailure(call: Call<UsuarioToken>, t: Throwable) {
//            // Falha na comunicação com o servidor
//            onResult(false, "Falha na comunicação: ${t.message}")
//        }
//    })
//}
//
//
//
//
//        @Composable
//        fun TelaCadastro() {
//            val context = LocalContext.current
//            var etapaAtual by remember { mutableStateOf(1) }
//            var usuarioInfo = remember { mutableStateOf(UsuarioCadastro()) }
//            var cadastroResult by remember { mutableStateOf<Pair<Boolean, String>?>(null) }
//
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = Color(0xFF4200A1) // Cor de fundo do app atualizada aqui
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Spacer(modifier = Modifier.height(32.dp)) // Espaço no topo
//                    Row(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp, 16.dp, 0.dp, 0.dp)) {
//                        Column(
//                            modifier = Modifier
//                                .weight(0.85f) // 80% do espaço disponível
//                        ) {
//                            if (etapaAtual == 1) {
//                                TextoEtapaCadastro(
//                                    titulo = "Aproveite!",
//                                    descricao = "Faça parte do projeto\ne aproveite benefícios\nexclusivos!"
//                                )
//                            } else if (etapaAtual == 2) {
//                                TextoEtapaCadastro(
//                                    titulo = "Estamos quase lá!",
//                                    descricao = "Você está a um passo\nde desfrutar dos\nbenefícios deste\nprojeto!"
//                                )
//                            }
//                        }
//
//                        Image(
//                            painter = painterResource(id = R.mipmap.persona), // Substitua pelo ID de recurso correto
//                            contentDescription = "Personagem com smartphone",
//                            modifier = Modifier
//                                .width(160.dp)
//                                .height(185.dp)
//                        )
//                    }
//
//                    Surface(
//                        modifier = Modifier
//                            .fillMaxWidth(), // Adiciona um padding em torno da Surface.
//                        shape = RoundedCornerShape(
//                            topStart = 25.dp, // Bordas arredondadas de 25dp no canto superior esquerdo.
//                            topEnd = 25.dp, // Bordas arredondadas de 25dp no canto superior direito.
//                            bottomStart = 0.dp, // Sem bordas arredondadas no canto inferior esquerdo.
//                            bottomEnd = 0.dp // Sem bordas arredondadas no canto inferior direito.
//                        ), // Bordas arredondadas em 25dp.
//                        color = Color.White // Fundo branco para a Surface.
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxHeight() // Faz com que a Column também ocupe toda a altura disponível.
//                                .padding(16.dp), // Adiciona um padding dentro da Column.
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                        ) {
//                            Spacer(modifier = Modifier.height(50.dp))
//                            Text(
//                                text = "Seja membro!",
//                                fontSize = 30.sp,
//                                color = Color(0xFF4200A1),
//                                fontWeight = FontWeight.Bold,
//                                modifier = Modifier.fillMaxWidth() // Preenche a largura da coluna
//                            )
//                            Spacer(modifier = Modifier.height(20.dp)) // Espaço no topo
//
//                            when (etapaAtual) {
//                                1 -> CadastroEtapaUm { nome, email, genero, orientacaoSexual ->
//                                    usuarioInfo.value = usuarioInfo.value.copy(
//                                        nome = nome,
//                                        email = email,
//                                        genero = genero,
//                                        orientacaoSexual = orientacaoSexual
//                                    )
//                                    etapaAtual = 2
//                                }
//
//                                2 -> {
//                                    CadastroEtapaDois(onCadastroCompleto = { cpf, senha, confirmarSenha ->
//                                        if (senha == confirmarSenha) {
//                                            usuarioInfo.value =
//                                                usuarioInfo.value.copy(cpf = cpf, senha = senha)
//                                            // Aqui a lógica para chamar performCadastro será adicionada.
//                                            performCadastro(usuarioInfo.value) { sucesso, mensagem ->
//                                                cadastroResult = Pair(sucesso, mensagem)
//                                                if (sucesso) {
//                                                    // Prossiga conforme necessário, por exemplo, navegar para outra tela.
//                                                } else {
//                                                    etapaAtual = 1 // ou mostrar erro
//                                                }
//                                            }
//                                        } else {
//                                            // Tratar caso as senhas não coincidam
//                                        }
//                                    })
//
//                                    // Mostra o resultado do cadastro (sucesso ou erro)
//                                    cadastroResult?.let {
//                                        Text("Resultado do cadastro: ${it.second}")
//                                    }
//                                }
//                            }
//                            Spacer(modifier = Modifier.height(10.dp))
//                            Row (  modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.Center){
//                                Text(
//                                    text = AnnotatedString("Já possui uma conta ? "),
//                                )
//                                ClickableText(
//                                    text = AnnotatedString("Faça Login"),
//                                    style = TextStyle(
//                                        color = Color(0xFF2C73EB), // Cor azul para o texto clicável
//                                        fontSize = 16.sp
//                                    ),
//                                    onClick = {
//                                        val intent = Intent(context, Login::class.java)
//                                        context.startActivity(intent)
//
//                                    },
//                                )
//                            }                        }
//
//                    }
//                }
//            }
//        }
//@Composable
//fun TextoEtapaCadastro(titulo: String, descricao: String) {
//    Text(
//        text = titulo,
//        color = Color.White,
//        fontSize = 25.sp,
//        fontWeight = FontWeight.Bold
//    )
//    Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//    Text(
//        text = descricao,
//        color = Color.White,
//        fontSize = 20.sp
//    )
//}
//@Composable
//fun CadastroEtapaUm(onProximo: (String, String, String, String) -> Unit) {
//    var nome by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var genero by remember { mutableStateOf("") }
//    var orientacaoSexual by remember { mutableStateOf("") }
//
//    Column {
//        OutlinedTextField(
//            value = nome,
//            onValueChange = { nome = it },
//            label = { Text("Insira seu nome") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Insira seu e-mail") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//        OutlinedTextField(
//            value = genero,
//            onValueChange = { genero = it },
//            label = { Text("Gênero") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//        OutlinedTextField(
//            value = orientacaoSexual,
//            onValueChange = { orientacaoSexual = it },
//            label = { Text("Orientação Sexual") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(20.dp)) // Espaço no topo
//        Button(onClick = { onProximo(nome, email, genero, orientacaoSexual) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp), // Defina a altura do botão conforme necessário
//            shape = RectangleShape, // Faz com que os cantos do botão sejam quadrados
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF4200A1) // Cor de fundo do botão
//            )) {
//            Text("Próximo")
//        }
//    }
//}
//
//
//@Composable
//fun CadastroEtapaDois(onCadastroCompleto: (String, String, String) -> Unit) {
//    var cpf by remember { mutableStateOf("") }
//    var senha by remember { mutableStateOf("") }
//    var confirmarSenha by remember { mutableStateOf("") }
//    var usuarioInfo = remember { mutableStateOf(UsuarioCadastro()) }
//
//
//    Column {
//        OutlinedTextField(
//            value = cpf,
//            onValueChange = { cpf = it },
//            label = { Text("Insira seu CPF") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//        OutlinedTextField(
//            value = senha,
//            onValueChange = { senha = it },
//            label = { Text("Insira sua nova senha") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//        OutlinedTextField(
//            value = confirmarSenha,
//            onValueChange = { confirmarSenha = it },
//            label = { Text("Confirme a senha") },
//            visualTransformation = PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
//        Button(onClick = {
//            if (senha == confirmarSenha) {
//                onCadastroCompleto(cpf, senha, confirmarSenha)
//                performCadastro(usuarioInfo.value) { sucesso, mensagem ->
//                }
//            } else {
//
//
//            }
//        }
//            ,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(48.dp), // Defina a altura do botão conforme necessário
//            shape = RectangleShape, // Faz com que os cantos do botão sejam quadrados
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF4200A1))) {
//            Text("Cadastrar-se")
//        }
//    }
//}
//
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewCadastro() {
//    TelaCadastro()
//}