package com.adevinta.mappicker.front.telasInscricao

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import retrofit2.Call
import com.adevinta.mappicker.ui.theme.pridepointsTheme
import retrofit2.Callback
import retrofit2.Response
import com.adevinta.mappicker.R
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.ViewModel.CadastroViewModel
import com.adevinta.mappicker.classes.ViewModel.SalvarLogin
import com.adevinta.mappicker.classes.entidades.UsuarioCadastro
import com.adevinta.mappicker.classes.entidades.UsuarioToken

class Cadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pridepointsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaCadastro()
                }
            }
        }
    }
}

@Composable
fun TelaCadastro(cadastroViewModel: CadastroViewModel = viewModel()) {
    val context = LocalContext.current
    var etapaAtual by remember { mutableStateOf(1) }
    var usuarioInfo by remember { mutableStateOf(UsuarioCadastro()) }
    val cadastroResult by cadastroViewModel.cadastroResult.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4200A1) // Cor de fundo do app atualizada aqui
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // Espaço no topo
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 0.dp, 0.dp)) {
                Column(
                    modifier = Modifier.weight(0.85f) // 80% do espaço disponível
                ) {
                    if (etapaAtual == 1) {
                        TextoEtapaCadastro(
                            titulo = stringResource(R.string.text_aproveite),
                            descricao = stringResource(R.string.text_aproveite_descricao)
                        )
                    } else if (etapaAtual == 2) {
                        TextoEtapaCadastro(
                            titulo = stringResource(R.string.text_estamos_quase_la),
                            descricao = stringResource(R.string.text_estamos_quase_la_descricao)
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.mipmap.persona), // Substitua pelo ID de recurso correto
                    contentDescription = stringResource(R.string.content_description_persona),
                    modifier = Modifier.width(160.dp).height(185.dp)
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth(), // Adiciona um padding em torno da Surface.
                shape = RoundedCornerShape(
                    topStart = 25.dp, // Bordas arredondadas de 25dp no canto superior esquerdo.
                    topEnd = 25.dp, // Bordas arredondadas de 25dp no canto superior direito.
                    bottomStart = 0.dp, // Sem bordas arredondadas no canto inferior esquerdo.
                    bottomEnd = 0.dp // Sem bordas arredondadas no canto inferior direito.
                ), // Bordas arredondadas em 25dp.
                color = Color.White // Fundo branco para a Surface.
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().padding(16.dp), // Adiciona um padding dentro da Column.
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = stringResource(R.string.text_seja_membro),
                        fontSize = 30.sp,
                        color = Color(0xFF4200A1),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth() // Preenche a largura da coluna
                    )
                    Spacer(modifier = Modifier.height(20.dp)) // Espaço no topo

                    when (etapaAtual) {
                        1 -> CadastroEtapaUm { nome, email, genero, orientacaoSexual ->
                            usuarioInfo = usuarioInfo.copy(
                                nome = nome,
                                email = email,
                                genero = genero,
                                orientacaoSexual = orientacaoSexual
                            )
                            etapaAtual = 2
                        }
                        2 -> CadastroEtapaDois { cpf, senha, confirmarSenha ->
                            if (senha == confirmarSenha) {
                                usuarioInfo = usuarioInfo.copy(cpf = cpf, senha = senha)
                                cadastroViewModel.cadastrarUsuario(usuarioInfo)
                            } else {
                                Toast.makeText(context, "As senhas não batem", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    cadastroResult?.let {
                        val (success, message) = it
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        if (success) {
                            // Redirecionar para a tela de login ou outra tela após o cadastro bem-sucedido
                            val intent = Intent(context, Login::class.java)
                            context.startActivity(intent)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(text = stringResource(R.string.text_ja_possui_conta))
                        ClickableText(
                            text = AnnotatedString(stringResource(R.string.text_faca_login)),
                            style = TextStyle(
                                color = Color(0xFF2C73EB), // Cor azul para o texto clicável
                                fontSize = 16.sp
                            ),
                            onClick = {
                                val intent = Intent(context, Login::class.java)
                                context.startActivity(intent)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextoEtapaCadastro(titulo: String, descricao: String) {
    Text(
        text = titulo,
        color = Color.White,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
    Text(
        text = descricao,
        color = Color.White,
        fontSize = 20.sp
    )
}

@Composable
fun CadastroEtapaUm(onProximo: (String, String, String, String) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var orientacaoSexual by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(stringResource(R.string.text_insira_nome)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.text_insira_email)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
        OutlinedTextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text(stringResource(R.string.text_genero)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
        OutlinedTextField(
            value = orientacaoSexual,
            onValueChange = { orientacaoSexual = it },
            label = { Text(stringResource(R.string.text_orientacao_sexual)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp)) // Espaço no topo
        Button(onClick = { onProximo(nome, email, genero, orientacaoSexual) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp), // Defina a altura do botão conforme necessário
            shape = RectangleShape, // Faz com que os cantos do botão sejam quadrados
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4200A1) // Cor de fundo do botão
            )) {
            Text(stringResource(R.string.text_proximo))
        }
    }
}

@Composable
fun CadastroEtapaDois(onCadastroCompleto: (String, String, String) -> Unit) {
    var cpf by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var usuarioInfo = remember { mutableStateOf(UsuarioCadastro()) }
    val context = LocalContext.current

    Column {
        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text(stringResource(R.string.text_insira_cpf)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text(stringResource(R.string.text_insira_senha)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
        OutlinedTextField(
            value = confirmarSenha,
            onValueChange = { confirmarSenha = it },
            label = { Text(stringResource(R.string.text_confirme_senha)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp)) // Espaço no topo
        Button(onClick = {
            if (senha == confirmarSenha) {
                onCadastroCompleto(cpf, senha, confirmarSenha)
            } else {
                Toast.makeText(context,"As senhas não batem", Toast.LENGTH_LONG).show()
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp), // Defina a altura do botão conforme necessário
            shape = RectangleShape, // Faz com que os cantos do botão sejam quadrados
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4200A1))) {
            Text(stringResource(R.string.text_cadastrar_se))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCadastro() {
    TelaCadastro()
}
