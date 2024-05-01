package scholl.sptech.pridepoints.front.telasInscricao

import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.Bundle
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import scholl.sptech.pridepoints.conexao.NetworkService
import scholl.sptech.pridepoints.R
import scholl.sptech.pridepoints.classes.UsuarioToken
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PridePointsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF4200A1) // Cor de fundo do app atualizada aqui
                ) {
                    LoginScreen { email, senha ->
                        realizarLogin(email, senha)
                    }
                }
            }
        }
    }

    private fun realizarLogin(email: String, senha: String) {
        val networkService = NetworkService()
        val context = this

        networkService.realizarLogin(email, senha).enqueue(object : Callback<UsuarioToken> {
            override fun onResponse(call: Call<UsuarioToken>, response: Response<UsuarioToken>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Login bem-sucedido!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Erro de login!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UsuarioToken>, t: Throwable) {
                Toast.makeText(context, "Falha na comunicação: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}
@Composable
fun LoginScreen(realizarLogin: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val context = LocalContext.current

    val imageModifier = Modifier
        .height(201.dp) // Valor aproximado convertido de px para dp.
        .width(123.dp) // Valor aproximado convertido de px para dp.

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4200A1) // Cor de fundo do app atualizada aqui
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp, 0.dp,0.dp)) {
                Column(
                    modifier = Modifier
                        .weight(0.8f) // 80% do espaço disponível
                ) {
                    Text(
                        text = "Seja bem-vindo!",
                        fontSize = 30.sp,
                        color = Color.White, // Definindo a cor do texto como branco
                        fontWeight = FontWeight.Bold, // Aplicando negrito ao texto
                        modifier = Modifier.fillMaxWidth() // Preenche a largura da coluna
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Digite sua credencial \ne encontre lugares\nseguros e inclusivos.",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth() // Preenche a largura da coluna
                    )
                }

                Image(
                    painter = painterResource(R.mipmap.vanessa), // Substitua pelo drawable correto
                    contentDescription = "Vanessa proto persona",
                    modifier = Modifier
                        .height(201.dp) // Valor aproximado convertido de px para dp.
                        .width(123.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth(), // Adiciona um padding em torno da Surface.
                shape = RoundedCornerShape(
                    topStart = 25.dp, // Bordas arredondadas de 25dp no canto superior esquerdo.
                    topEnd = 25.dp, // Bordas arredondadas de 25dp no canto superior direito.
                    bottomStart = 0.dp, // Sem bordas arredondadas no canto inferior esquerdo.
                    bottomEnd = 0.dp // Sem bordas arredondadas no canto inferior direito.
                ), // Bordas arredondadas em 25dp.
                color = Color.White // Fundo branco para a Surface.
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight() // Faz com que a Column também ocupe toda a altura disponível.
                        .padding(16.dp), // Adiciona um padding dentro da Column.
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento entre os componentes.
                ) {
                    Spacer(modifier = Modifier.height(80.dp))
                    Text(
                        text = "Login",
                        fontSize = 30.sp,
                        color = Color(0xFF4200A1),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth() // Preenche a largura da coluna
                    )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Digite seu E-mail") },
                    modifier = Modifier.fillMaxWidth()
                )
                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Digite sua senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { realizarLogin(email, senha) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp), // Defina a altura do botão conforme necessário
                        shape = RectangleShape, // Faz com que os cantos do botão sejam quadrados
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4200A1) // Cor de fundo do botão
                        )
                    ) {
                    Text(
                        text = "Entrar",
                        fontSize = 24.sp,
                        color = Color.White,
                    )
                }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (  modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center){
                        Text(
                            text = AnnotatedString("Não possui uma conta ? "),
                        )
                        ClickableText(
                            text = AnnotatedString("Cadastre-se!"),
                            style = TextStyle(
                                color = Color(0xFF2C73EB), // Cor azul para o texto clicável
                                fontSize = 16.sp
                            ),
                            onClick = {
                                val intent = Intent(context, Cadastro::class.java)
                                context.startActivity(intent)
                                      
                            },
                        )
                    }

                }
                    
            }
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF4200A1 )
@Composable
fun DefaultPreview() {
    PridePointsTheme {
        LoginScreen { _, _ ->
            // Preview não irá executar a lógica de login
        }
    }
}
