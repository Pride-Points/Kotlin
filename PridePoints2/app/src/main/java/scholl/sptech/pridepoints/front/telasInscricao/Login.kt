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
import scholl.sptech.pridepoints.R
import scholl.sptech.pridepoints.api.RetrofitService
import scholl.sptech.pridepoints.classes.entidades.Credenciais
import scholl.sptech.pridepoints.classes.ViewModel.SalvarLogin
import scholl.sptech.pridepoints.classes.entidades.UsuarioToken
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import scholl.sptech.pridepoints.classes.ViewModel.LoginViewModel
import scholl.sptech.pridepoints.front.TelaInicialUsuario
import scholl.sptech.pridepoints.front.eventosregiao.HomeEventos
import scholl.sptech.pridepoints.front.fragmentos.HomeScreen

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PridePointsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF4200A1)
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val loginResult by loginViewModel.loginResult.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF4200A1)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 0.dp, 0.dp)) {
                Column(
                    modifier = Modifier.weight(0.8f)
                ) {
                    Text(
                        text = "Seja bem-vindo!",
                        fontSize = 30.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Digite sua credencial \ne encontre lugares\nseguros e inclusivos.",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Image(
                    painter = painterResource(R.mipmap.vanessa),
                    contentDescription = "Vanessa proto persona",
                    modifier = Modifier
                        .height(201.dp)
                        .width(123.dp)
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(80.dp))
                    Text(
                        text = "Login",
                        fontSize = 30.sp,
                        color = Color(0xFF4200A1),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
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
                        onClick = { loginViewModel.realizarLogin(context,email, senha) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4200A1))
                    ) {
                        Text(
                            text = "Entrar",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                    }

                    loginResult?.let {
                        val (success, message) = it
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        if (success) {
                            // Redirecionar para a tela principal após login bem-sucedido
                            context.startActivity(Intent(context, TelaInicialUsuario::class.java))

                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(text = AnnotatedString("Não possui uma conta? "))
                        ClickableText(
                            text = AnnotatedString("Cadastre-se!"),
                            style = TextStyle(color = Color(0xFF2C73EB), fontSize = 16.sp),
                            onClick = {
                                val intent = Intent(context, Cadastro::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF4200A1)
@Composable
fun DefaultPreview() {
    PridePointsTheme {
        LoginScreen()
    }
}
