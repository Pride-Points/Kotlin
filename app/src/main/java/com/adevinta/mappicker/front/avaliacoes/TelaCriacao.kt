package com.adevinta.mappicker.front.avaliacoes


import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adevinta.mappicker.R
import com.adevinta.mappicker.avaliacoes.AvaliacaoViewModel
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Observer
import com.adevinta.mappicker.classes.entidades.AvaliacaoCriacaoDTO


class TelaCriacao : ComponentActivity() {
    private val viewModel: AvaliacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        var empresaId = intent.getLongExtra("EMPRESA_ID2", -1)

        super.onCreate(savedInstanceState)
        if(empresaId.equals(-1)){
            empresaId = 1.toLong()
        }
        viewModel.carregarAvaliacoesDaEmpresa(this, empresaId)

        viewModel.avaliacoes.observe(this, Observer { avaliacoes ->
            setContent {
                TelaCriacao(
                    activity = this,
                    avaliacaoViewModel = viewModel,
                    empresaId = empresaId
                )
            }
        })

        viewModel.erroApi.observe(this, Observer { erro ->
            Log.e("api", "Erro na API: $erro")
        })
    }
}
    @Composable
    fun TelaCriacao(activity: Activity, avaliacaoViewModel: AvaliacaoViewModel, empresaId: Long) {
        var selectedButton by remember { mutableStateOf("") }

        val context = LocalContext.current

        val text = buildAnnotatedString {
            append("Como foi sua ")
            withStyle(style = SpanStyle(color = Color(0xFF5800D6))) {
                append("experiência")
            }
            append("?")
        }

        var novaEdit by remember { mutableStateOf(AvaliacaoCriacaoDTO(nota = 0.0, comentario = "", tag = "")) }
        val totalEstrelas = 5
        var estrelasSelecionadas by remember { mutableStateOf(0) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(id = R.mipmap.arrow_left_voltar),
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        activity.finish()
                    }
            )

            Spacer(modifier = Modifier.weight(2f))
            Text(text = "Criação", Modifier.padding(vertical = 15.dp))
            Spacer(modifier = Modifier.weight(3f))
        }
        Divider(
            color = Color(android.graphics.Color.parseColor("#5800D6")),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = text, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            (1..totalEstrelas).forEach { index ->
                Image(
                    painter = painterResource(
                        id = if (index <= estrelasSelecionadas) R.mipmap.estrela_amarela else R.mipmap.estrela_cinza
                    ),
                    contentDescription = if (index <= estrelasSelecionadas) "Estrela Amarela" else "Estrela Cinza",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            estrelasSelecionadas = index
                            novaEdit = novaEdit.copy(nota = index.toDouble())
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Como se sentiu durante o tempo permanecido no estabelecimento?",
            modifier = Modifier.fillMaxWidth(0.7f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.Center) {
                val defaultColors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color(0xFF000000)
                )

                val selectedColors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFCCCCCC),
                    contentColor = Color(0xFF000000)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        colors = if (selectedButton == "Amade") selectedColors else defaultColors,
                        onClick = { selectedButton = "Amade" }
                    ) {
                        Text(text = "Amade")
                    }

                    Button(
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        colors = if (selectedButton == "Brave") selectedColors else defaultColors,
                        onClick = { selectedButton = "Brave" }
                    ) {
                        Text(text = "Brave")
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        colors = if (selectedButton == "Acolhide") selectedColors else defaultColors,
                        onClick = { selectedButton = "Acolhide" }
                    ) {
                        Text(text = "Acolhide")
                    }

                    Button(
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        colors = if (selectedButton == "Frustrade") selectedColors else defaultColors,
                        onClick = { selectedButton = "Frustrade" }
                    ) {
                        Text(text = "Frustrade")
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        colors = if (selectedButton == "Feliz") selectedColors else defaultColors,
                        onClick = { selectedButton = "Feliz" }
                    ) {
                        Text(text = "Feliz")
                    }

                    Button(
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                        colors = if (selectedButton == "Humilhade") selectedColors else defaultColors,
                        onClick = { selectedButton = "Humilhade" }
                    ) {
                        Text(text = "Humilhade")
                    }
                }
            }

        Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                label = { Text(text = "Comentário") },
                value = novaEdit.comentario ?: "",
                onValueChange = { novaEdit = novaEdit.copy(comentario = it) }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(modifier = Modifier.size(120.dp, 60.dp), onClick = {
                avaliacaoViewModel.criarAvaliacao(
                    context = activity,
                    avaliacao = novaEdit,
                    idEmpresa = empresaId,
                    onSuccess = {

                        val intent = Intent(context, TelaAvaliacaoActivity::class.java).apply {
                            putExtra("EMPRESA_ID", empresaId)
                        }
                        context.startActivity(intent)
                    },
                    onError = { errorMessage ->
                        val errorM = novaEdit.toString() + empresaId.toString()
                        Log.e("API", errorM)
                    }
                )
            }) {
                Text(text = "Avaliar", fontSize = 20.sp)
            }
        }
    }


