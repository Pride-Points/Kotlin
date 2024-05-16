package scholl.sptech.pridepoints.menuitens.avaliacoes

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import scholl.sptech.pridepoints.R
import scholl.sptech.pridepoints.classes.entidades.Avaliacao
import scholl.sptech.pridepoints.classes.ViewModel.AvaliacaoViewModel
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme

class TelaAvaliar : ComponentActivity() {
    @RequiresApi(android.os.Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PridePointsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AvaliarScreen(this, AvaliacaoViewModel())
                }
            }
        }
    }
}

@RequiresApi(android.os.Build.VERSION_CODES.TIRAMISU)
@Composable
fun AvaliarScreen(activity: Activity, avaliacaoViewModel: AvaliacaoViewModel) {
    val context = LocalContext.current

    val totalEstrelas = 5
    var estrelasSelecionadas by remember { mutableStateOf(0) }
    val avaliacao = remember { mutableStateOf(Avaliacao(id = 0, loja = "", estrelas = estrelasSelecionadas)) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        TopBarAvaliar(activity)
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Avalie sua experiência", fontSize = 20.sp, textAlign = TextAlign.Center)

        // Exemplo de criação de elementos visuais para avaliação
        EstrelasAvaliacao(totalEstrelas, estrelasSelecionadas) { selected ->
            estrelasSelecionadas = selected
            avaliacao.value = avaliacao.value.copy(estrelas = selected)
        }

        Spacer(modifier = Modifier.height(30.dp))
        avaliacao.value.comentario?.let {
            OutlinedTextField(
                label = { Text(text = "Comentário") },
                value = it,
                onValueChange = { avaliacao.value = avaliacao.value.copy(comentario = it) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                avaliacaoViewModel.criarAvaliacao(avaliacao.value, getUserIdFromPreferences(context), getEmpresaIdFromPreferences(context), onSuccess = {
                    activity.finish()
                }, onError = {
                    // Lidar com erro
                })
            },
            modifier = Modifier.size(160.dp, 50.dp)
        ) {
            Text(text = "Enviar Avaliação", fontSize = 18.sp)
        }
    }
}

@Composable
fun TopBarAvaliar(activity: Activity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.mipmap.arrow_left_voltar),
            contentDescription = "Voltar",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    activity.finish()
                }
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Nova Avaliação", Modifier.padding(vertical = 15.dp), fontSize = 20.sp)
    }
    Divider(color = Color(android.graphics.Color.parseColor("#5800D6")), thickness = 1.dp)
}

fun getUserIdFromPreferences(context: Context): Long {
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val userIdString = sharedPreferences.getString("USER_ID", "")
    return userIdString?.toLongOrNull() ?: -1L  // Retorna -1 se não for possível converter
}

fun getEmpresaIdFromPreferences(context: Context): Long {
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val userIdString = sharedPreferences.getString("EMPRESA_ID", "")
    return userIdString?.toLongOrNull() ?: -1L  // Retorna -1 se não for possível converter
}

@Composable
fun EstrelasAvaliacao(total: Int, selected: Int, onStarSelected: (Int) -> Unit) {
    Row {
        (1..total).forEach { index ->
            Image(
                painter = painterResource(
                    id = if (index <= selected) R.mipmap.estrela_amarela else R.mipmap.estrela_cinza
                ),
                contentDescription = if (index <= selected) "Estrela Cheia" else "Estrela Vazia",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onStarSelected(index) }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun AvaliarPreview() {
    PridePointsTheme {
        AvaliarScreen(Activity(), AvaliacaoViewModel())
    }
}
