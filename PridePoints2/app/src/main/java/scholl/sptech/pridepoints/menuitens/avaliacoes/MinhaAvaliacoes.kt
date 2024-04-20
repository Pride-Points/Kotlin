package scholl.sptech.pridepoints.menuitens.avaliacoes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Response
import scholl.sptech.pridepoints.R
import scholl.sptech.pridepoints.menuitens.RetrofitMock

@Composable
fun ModalEditar(
    showDialog: Boolean,
    onCloseDialog: () -> Unit
) {
    if (showDialog) {

        AlertDialog(
            onDismissRequest = onCloseDialog,
            title = { Text(text = "Gostaria de editar esta avaliação?") },
            confirmButton = {
                Button(onClick = onCloseDialog,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color.White), modifier = Modifier.border(
                        1.dp, Color.Black, RoundedCornerShape(100.dp))
                ) {
                    Text(text = "Sim", color = Color.Black)
                }
            },
            dismissButton = {
                Button(onClick = onCloseDialog) {
                    Text(text = "Não")
                }
            },
            shape = RoundedCornerShape(16.dp),
        )

    }
}

@Composable
fun MinhasAvaliacoes() {
    val avals = remember {
        mutableStateListOf<Avaliacoes>()
    }

    val apiAval = RetrofitMock.getApiAvaliacoes()

    val get = apiAval.get()

    get.enqueue(object : retrofit2.Callback<List<Avaliacoes>> {
        override fun onResponse(
            call: Call<List<Avaliacoes>>,
            response: Response<List<Avaliacoes>>
        ) {
            if (response.isSuccessful) {
                val lista = response.body()
                if (lista != null) {
                    avals.clear()
                    avals.addAll(lista)
                }
            }
        }

        override fun onFailure(call: Call<List<Avaliacoes>>, t: Throwable) {

        }
    })

    Column {
        if (avals.isEmpty()) {
            Text(text = "Faça sua primeira avaliação para ela aparecer aqui :)")
        } else {
            Text("Suas avaliações!")

            LazyColumn(Modifier.height(707.dp)) {
                items(items = avals) { aval ->
                    var isExpanded: Boolean by remember {
                        mutableStateOf(false)
                    }

                    var showDialog by remember {
                        mutableStateOf(false)
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Adicionando sombra fora do Box
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(0.9f)
                                .shadow(5.dp, shape = RoundedCornerShape(4.dp), clip = true)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                    .background(Color.White)
                                    .padding(8.dp)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = "${aval.loja}",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp
                                            )
                                        )
                                        Spacer(modifier = Modifier.weight(1f))

                                        val totalEstrelas = 5
                                        val estrelasAmarelas = aval.estrelas
                                        val estrelasCinza = totalEstrelas - estrelasAmarelas
                                        Row {
                                            repeat(estrelasAmarelas) {
                                                Image(
                                                    painter = painterResource(id = R.mipmap.estrela_amarela),
                                                    contentDescription = "Estrela Amarela",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                            }

                                            repeat(estrelasCinza) {
                                                Image(
                                                    painter = painterResource(id = R.mipmap.estrela_cinza),
                                                    contentDescription = "Estrela Cinza",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                            }
                                        }
                                    }

                                    val maxPreviewLength = 50
                                    val textToShow =
                                        if (isExpanded || aval.comentario!!.length <= maxPreviewLength) {
                                            aval.comentario
                                        } else {
                                            "${aval.comentario.take(maxPreviewLength)}... "
                                        }
                                    val annotatedText = buildAnnotatedString {
                                        append(textToShow)
                                        if (!isExpanded && aval.comentario!!.length > maxPreviewLength) {
                                            withStyle(
                                                style = SpanStyle(
                                                    color = Color(0xFF5800D6),
                                                    fontWeight = FontWeight.Bold
                                                )
                                            ) {
                                                append("Ver mais")
                                            }
                                        }
                                    }

                                    ClickableText(
                                        text = annotatedText,
                                        onClick = { offset ->
                                            if (offset in textToShow!!.length until annotatedText.length) {
                                                isExpanded = true
                                            }
                                        },
                                        style = TextStyle(fontSize = 14.sp)
                                    )


                                    Row(
                                        verticalAlignment = Alignment.Bottom,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    ) {
                                        if (isExpanded) {
                                            Button(
                                                onClick = { isExpanded = false },
                                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                                contentPadding = PaddingValues(0.dp), // Remove padding
                                                elevation = ButtonDefaults.buttonElevation(
                                                    defaultElevation = 0.dp,
                                                    pressedElevation = 0.dp,
                                                    hoveredElevation = 0.dp,
                                                    focusedElevation = 0.dp
                                                )
                                            ) {
                                                Text("Ver menos", color = Color(0xFF5800D6))
                                            }

                                        }

                                        /*if (!isExpanded && aval.comentario!!.length > 100) {
                                            Button(onClick = { isExpanded = true }) {
                                                Text("Ver mais")
                                            }
                                        }
                                        if (isExpanded) {
                                            Button(onClick = { isExpanded = false }) {
                                                Text("Ver menos")
                                            }
                                        }
*/
                                        Spacer(modifier = Modifier.weight(1f))


                                        Image(
                                            painter = painterResource(id = R.mipmap.editar_avaliacao),
                                            contentDescription = "editar",
                                            Modifier
                                                .size(27.dp)
                                                .clickable { showDialog = true }
                                        )


                                        Image(
                                            painter = painterResource(id = R.mipmap.linha_avaliacao),
                                            contentDescription = "editar",
                                            Modifier.size(27.dp)
                                        )

                                        Image(
                                            painter = painterResource(id = R.mipmap.deletar_avaliacao),
                                            contentDescription = "editar",
                                            Modifier
                                                .size(27.dp)
                                                .clickable { }

                                        )

                                        ModalEditar(
                                            showDialog = showDialog,
                                            onCloseDialog = { showDialog = false }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
