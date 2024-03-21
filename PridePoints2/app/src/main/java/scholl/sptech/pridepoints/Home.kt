package scholl.sptech.pridepoints

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme
class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PridePointsTheme {
                // Defina o background do app aqui usando Surface
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF4200A1) // Cor de fundo do app
                ) {
                    PridePointsWelcomeScreen()
                }
            }
        }
    }
}



@Composable
fun PridePointsWelcomeScreen(backgroundColor: Long = 0xFF4200A1) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(80.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.mipmap.logo_light), // Substitua `your_image_name` pelo nome do arquivo de imagem
                    contentDescription = "Logo Pride Points", // Forneça uma descrição para acessibilidade
                    modifier = Modifier.size(width = 231.dp, height = 142.dp) // Definindo o tamanho
                )
            }
            Spacer(modifier = Modifier.height(80.dp))
            Row {
                Text(
                    text = "Navegue ",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "pelo",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 40.sp,
                    )
                )
            }
            Row{

                Text(
                    text = "mundo e ",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 40.sp,
                    )
                )
                Text(
                    text = "encontre",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Row{
                Text(
                    text = "seu Lugar!",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(60.dp))
            OutlinedButton(
                onClick = { /* TODO: Add sign up logic */ },
                border = BorderStroke(1.dp, Color.White),
                shape = RectangleShape, // Isso torna o botão com cantos quadrados
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White), // Define a cor de fundo do botão para branco
                modifier = Modifier.width(300.dp) // Definindo a largura para um valor específico
            ) {
                Text(
                    text = "Cadastre-se",
                    style = TextStyle(
                        color = Color(0xFF4200A1), // Hexadecimal cor do texto
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            val annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp)) {
                    append("Já possui uma conta ? ")
                }
                // Parte clicável
                withStyle(style = SpanStyle(color = Color.White, fontSize = 16.sp,   textDecoration = TextDecoration.Underline)) {
                    append("Faça o Login!")
                }
            }
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    if (offset >= annotatedText.length - "Faça o Login!".length) {
                        // Agora, em vez de mudar um estado, vamos iniciar a atividade Login
                        context.startActivity(Intent(context, Login::class.java))
                    }
                }
            )

        }

            Spacer(modifier = Modifier.height(32.dp))

        }
    }


@Preview(showBackground = true, backgroundColor = 0xFF4200A1)
@Composable
fun PreviewPridePointsWelcomeScreen() {
    PridePointsTheme {
        // Como a função onNavigateToLogin não fará nada no preview, passa-se uma função vazia
        PridePointsWelcomeScreen()
    }
}