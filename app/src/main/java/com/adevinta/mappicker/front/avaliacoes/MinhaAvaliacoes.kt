package com.adevinta.mappicker.menuitens.avaliacoes

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adevinta.mappicker.R
import com.adevinta.mappicker.avaliacoes.AvaliacaoViewModel
import com.adevinta.mappicker.classes.entidades.Avaliacao
import com.adevinta.mappicker.classes.entidades.AvaliacaoDTO

@Composable
fun ModalDeletar(
    showDialogDelete: Boolean,
    onCloseDialog: () -> Unit,
    onConfirmDialog: () -> Unit
) {
    if (showDialogDelete) {

        AlertDialog(
            onDismissRequest = onCloseDialog,
            title = { Text(text = "Gostaria de excluir esta avaliação?") },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmDialog()
                        onCloseDialog()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color.White
                    ), modifier = Modifier.border(
                        1.dp, Color.Black, RoundedCornerShape(100.dp)
                    )
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
fun ModalEditar(
    showDialogEdit: Boolean,
    onCloseDialog: () -> Unit,
    onConfirmDialog: () -> Unit
) {
    if (showDialogEdit) {

        AlertDialog(
            onDismissRequest = onCloseDialog,
            title = { Text(text = "Gostaria de editar esta avaliação?") },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmDialog()
                        onCloseDialog()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        contentColor = Color.White
                    ), modifier = Modifier.border(
                        1.dp, Color.Black, RoundedCornerShape(100.dp)
                    )
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
fun MinhasAvaliacoes(avaliacaoViewModel: AvaliacaoViewModel = AvaliacaoViewModel(), context: Context = LocalContext.current) {

    val avals by avaliacaoViewModel.avaliacoes.observeAsState(emptyList())

    // Carrega as avaliações quando a composição é iniciada
    LaunchedEffect(Unit) {
        avaliacaoViewModel.carregarAvaliacoesDoUsuario(context)
    }

    Column {
        if (avals.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Faça sua primeira avaliação!",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Suas avaliações!",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )
                )
            }

            LazyColumn(modifier = Modifier
                .heightIn(min = 100.dp, max = 550.dp)
                .fillMaxWidth()) {
                items(items = avals) { aval ->
                    var isExpanded by remember { mutableStateOf(false) }
                    var showDialogEdit by remember { mutableStateOf(false) }
                    var showDialogDelete by remember { mutableStateOf(false) }
                    var selectedItem by remember { mutableStateOf<AvaliacaoDTO?>(null) }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
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
                                    val contexto = LocalContext.current

                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = aval.nomeAvaliador ?: "",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp
                                            )
                                        )
                                        Spacer(modifier = Modifier.weight(1f))

                                        val totalEstrelas = 5
                                        val estrelasAmarelas = aval.nota.toInt()
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

                                    // Ver mais e limitação de caracteres
                                    val maxPreviewLength = 50
                                    val textToShow =
                                        if (isExpanded || aval.comentario!!.length <= maxPreviewLength) {
                                            aval.comentario
                                        } else {
                                            "${aval.comentario!!.take(maxPreviewLength)}... "
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

                                        // Ver menos
                                        if (isExpanded) {
                                            Button(
                                                onClick = { isExpanded = false },
                                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                                contentPadding = PaddingValues(0.dp),
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

                                        Spacer(modifier = Modifier.weight(1f))

                                        Image(
                                            painter = painterResource(id = R.mipmap.editar_avaliacao),
                                            contentDescription = "editar",
                                            Modifier
                                                .size(27.dp)
                                                .clickable {
                                                    showDialogEdit = true
                                                    selectedItem = aval
                                                }
                                        )

                                        Image(
                                            painter = painterResource(id = R.mipmap.linha_avaliacao),
                                            contentDescription = "linha",
                                            Modifier.size(27.dp)
                                        )

                                        Image(
                                            painter = painterResource(id = R.mipmap.deletar_avaliacao),
                                            contentDescription = "delete",
                                            Modifier
                                                .size(27.dp)
                                                .clickable {
                                                    showDialogDelete = true
                                                    selectedItem = aval
                                                }
                                        )

                                        if (showDialogDelete) {
                                            ModalDeletar(
                                                showDialogDelete = showDialogDelete,
                                                onCloseDialog = { showDialogDelete = false },
                                                onConfirmDialog = {
                                                    selectedItem?.id?.let { id ->
                                                        avaliacaoViewModel.removerAvaliacao(context, id)
                                                    }
                                                    showDialogDelete = false
                                                }
                                            )
                                        }

                                        if (showDialogEdit) {
                                            ModalEditar(
                                                showDialogEdit = showDialogEdit,
                                                onCloseDialog = { showDialogEdit = false },
                                                onConfirmDialog = {
                                                    selectedItem?.let {
                                                        val telaEdit = Intent(
                                                            contexto,
                                                            TelaEdicao::class.java
                                                        )
                                                        telaEdit.putExtra("itemSelecionado", it)
                                                        contexto.startActivity(telaEdit)
                                                    }
                                                }
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
}
