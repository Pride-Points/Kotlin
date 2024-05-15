package scholl.sptech.pridepoints.perfilUsuario


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


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun TelaAvaliacao() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        TopoDaTela()
        LinhaRoxa()

        Spacer(modifier = Modifier.height(40.dp))

        // Titulo do estabelecimento e nota
        TituloENota(estabelecimento = "Sucos da Vandinha", nota = 4.5f)

        // Descrição do estabelecimento
        DescricaoDoEstabelecimento(descricao = "Banca de diferentes sabores de sucos...")

        // Botão "Avalie"
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

        // Avaliações
        Avaliacoes()

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
        ){
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
fun Avaliacoes() {

    repeat(1) {
        AvaliacaoCard(url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvBqW2mFZKxv6Ap__3JOeSf0to24zpawpUTLb_rJC30w&s")
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AvaliacaoCard(url: String) {

    val pessoaComentario = pessoaFisica(
        nome = "Tamiris Janilda",
        comentario = "Explorando as últimas tendências em IA e Big Data",
        dtComentario = "18/09/2023"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = RoundedCornerShape(8.dp)
                clip = true
                translationX = -4.dp.toPx()
                translationY = 6.dp.toPx()
            }
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {

            AsyncImage(
                model = url,
                contentDescription = "Image",
                contentScale = ContentScale.None,
                modifier = Modifier.size(37.dp))

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Row {

                    Text(
                        text = pessoaComentario.nome ?: "",
                        style = TextStyle(fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.width(100.dp))

                    Row {

                        Image(
                            painter = painterResource(R.mipmap.ic_nota),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 2.dp)
                                .size(10.dp)
                        )

                        Image(
                            painter = painterResource(id = R.mipmap.ic_nota),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 2.dp)
                                .size(10.dp)
                        )

                        Image(
                            painter = painterResource(id = R.mipmap.ic_nota),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 2.dp)
                                .size(10.dp)
                        )

                        Image(
                            painter = painterResource(id = R.mipmap.ic_nota),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 2.dp)
                                .size(10.dp)
                        )

                        Image(
                            painter = painterResource(id = R.mipmap.ic_nota_cinza),
                            contentDescription = null,
                            modifier = Modifier
                                .size(10.dp)
                        )
                    }

                }

                Text(
                    text = "${pessoaComentario.comentario ?: ""}",
                    style = TextStyle(fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = pessoaComentario.dtComentario ?: "",
                        style = TextStyle(fontSize = 14.sp, color = Color(0xFF5800D6)),
                        modifier = Modifier.padding(start = 195.dp)
                    )
                }
            }
        }
    }
}




data class pessoaFisica (
    val nome: String?,
    val comentario: String?,
    val dtComentario: String?
)

@Preview(showBackground = true)
@Composable
fun TelaAvaliacaoPreview() {
    TelaAvaliacao()
}




