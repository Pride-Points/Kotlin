package com.adevinta.mappicker.front.avaliacoes


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight
import com.adevinta.mappicker.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.Observer
import coil.compose.AsyncImage
import com.adevinta.mappicker.avaliacoes.AvaliacaoViewModel
import com.adevinta.mappicker.classes.entidades.AvaliacaoDTO

class TelaAvaliacaoActivity : ComponentActivity() {

    private val viewModel: AvaliacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val empresaId = intent.getLongExtra("EMPRESA_ID", -1)

        viewModel.carregarAvaliacoesDaEmpresa(this, empresaId)

        viewModel.avaliacoes.observe(this, Observer { avaliacoes ->
            setContent {
                if(avaliacoes.isNullOrEmpty()){
                    TelaAvaliacao(avaliacoes = emptyList())
                }else{
                    TelaAvaliacao(avaliacoes)
                }
            }
        })

        viewModel.erroApi.observe(this, Observer { erro ->
            Log.e("api", "EROOOO NA APAIIIII")

        })
    }
}

@Composable
fun TelaAvaliacao(avaliacoes: List<AvaliacaoDTO>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        TopoDaTela()
        LinhaRoxa()
        Spacer(modifier = Modifier.height(40.dp))

        if (avaliacoes.isNotEmpty()) {
            TituloENota(estabelecimento = "Nome do Estabelecimento", nota = avaliacoes[0].nota.toFloat())
            DescricaoDoEstabelecimento(descricao = "Horario de funcionamento: 08:00 - 18:00")
            Button(
                onClick = { /* Ação ao clicar no botão */ },
                modifier = Modifier
                    .padding(top = 1.dp)
                    .width(130.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF5800D6)),
                contentPadding = PaddingValues(vertical = 10.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Avalie",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Avaliacoes(avaliacoes)
        } else {
            TituloENota(estabelecimento = "Nome do Estabelecimento", 0.toFloat())
            DescricaoDoEstabelecimento(descricao = "Horario de funcionamento: 08:00 - 18:00")
            Button(
                onClick = { /* Ação ao clicar no botão */ },
                modifier = Modifier
                    .padding(top = 1.dp)
                    .width(130.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF5800D6)),
                contentPadding = PaddingValues(vertical = 10.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Avalie",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "Essa empresa não tem avaliação",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TopoDaTela() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.mipmap.seta_esquerda),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "Avaliações",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 90.dp)
            )
        }
    }
}

@Composable
fun LinhaRoxa() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0xFF5800D6))
    )
}

@Composable
fun TituloENota(estabelecimento: String, nota: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = estabelecimento,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "- $nota",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 10.dp),
                color = Color(0xFF5800D6)
            )
            Image(
                painter = painterResource(id = R.mipmap.ic_nota),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 40.dp)
                    .size(15.dp)
            )
        }
    }
}

@Composable
fun DescricaoDoEstabelecimento(descricao: String) {
    Text(
        text = descricao,
        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
    )
}

@Composable
fun Avaliacoes(avaliacoes: List<AvaliacaoDTO>) {
    Column {
        for (avaliacao in avaliacoes) {
            AvaliacaoCard(avaliacao)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun AvaliacaoCard(avaliacao: AvaliacaoDTO) {
    val pessoaComentario = PessoaFisica(
        nome = avaliacao.nomeAvaliador,
        comentario = avaliacao.comentario,
        dtComentario = "06-06-2024"
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                clip = true
                translationX = -4.dp.toPx()
                translationY = 6.dp.toPx()
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvBqW2mFZKxv6Ap__3JOeSf0to24zpawpUTLb_rJC30w&s",
                contentDescription = "Image",
                modifier = Modifier.size(37.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Row {
                    Text(
                        text = pessoaComentario.nome ?: "",
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(100.dp))
                    Row {
                        repeat(avaliacao.nota.toInt()) {
                            Image(
                                painter = painterResource(R.mipmap.ic_nota),
                                contentDescription = null,
                                modifier = Modifier.size(10.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                        }
                        Image(
                            painter = painterResource(R.mipmap.ic_nota_cinza),
                            contentDescription = null,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
                Text(
                    text = pessoaComentario.comentario ?: "",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = pessoaComentario.dtComentario ?: "",
                        fontSize = 14.sp,
                        color = Color(0xFF5800D6),
                        modifier = Modifier.padding(start = 195.dp)
                    )
                }
            }
        }
    }
}

data class PessoaFisica(
    val nome: String?,
    val comentario: String?,
    val dtComentario: String?
)

@Preview(showBackground = true)
@Composable
fun TelaAvaliacaoPreview() {
    TelaAvaliacao(emptyList())
}