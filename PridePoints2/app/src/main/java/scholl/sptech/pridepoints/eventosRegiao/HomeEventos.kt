package scholl.sptech.pridepoints.eventosRegiao

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp


@Composable
fun HomeEventos(EventoViewModel: EventoViewModel = EventoViewModel()) {
    val events = EventoViewModel.eventos.observeAsState().value.orEmpty()

    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier
            .height(540.dp)
            .padding(10.dp)
    ) {
        if (events.isNotEmpty()) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .simpleVerticalScrollbar(scrollState)
            ) {
                items(events.size) { index ->
                    EventCard(evento = events[index])
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Nenhum evento encontrado", style = TextStyle(fontSize = 20.sp, color = Color.Black))
            }
        }
    }
}

@Composable
fun EventCard(evento: EventosRegiao) {
    var isExpanded by remember { mutableStateOf(false) }

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
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                evento.nome?.let {
                    Text(
                        text = it,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "São Paulo",
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            // Modificado para usar ClickableText
            val maxPreviewLength = 100
            val textToShow = if (isExpanded || evento.descricaoEvento!!.length <= maxPreviewLength) {
                evento.descricaoEvento
            } else {
                "${evento.descricaoEvento!!.take(maxPreviewLength)}... "
            }
            val annotatedText = buildAnnotatedString {
                append(textToShow)
                if (!isExpanded && evento.descricaoEvento!!.length > maxPreviewLength) {
                    withStyle(style = SpanStyle(color = Color(0xFF5800D6), fontWeight = FontWeight.Bold)) {
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
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 0.dp, hoveredElevation = 0.dp, focusedElevation = 0.dp)
                    ) {
                        Text("Ver menos", color = Color(0xFF5800D6))
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
                Text(evento.dtEvento.toString(), color = Color(0xFF5800D6))
            }
        }
    }
}

@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp,
    scaleFactor: Float = 0.45f  // Fator de escala para a altura da scrollbar
): Modifier {
    val duration = 150  // Duração para animações rápidas
    val alpha by animateFloatAsState(
        targetValue = 1f,  // Scrollbar sempre visível enquanto há conteúdo suficiente
        animationSpec = tween(durationMillis = duration)
    )

    return this.then(drawWithContent {
        drawContent()

        // Sempre desenhe a scrollbar se houver itens suficientes
        if (state.layoutInfo.totalItemsCount > 0) {
            val totalContentHeight = state.layoutInfo.totalItemsCount * state.layoutInfo.viewportSize.height / state.layoutInfo.visibleItemsInfo.size
            val visibleHeight = state.layoutInfo.viewportSize.height
            var scrollbarHeight = visibleHeight * visibleHeight / totalContentHeight * scaleFactor
            var scrollbarOffsetY = (state.firstVisibleItemScrollOffset.toFloat() + state.firstVisibleItemIndex * visibleHeight) / totalContentHeight * visibleHeight

            // Ajuste para garantir que a scrollbar não ultrapasse a LazyColumn
            if (scrollbarOffsetY + scrollbarHeight > visibleHeight) {
                scrollbarHeight = visibleHeight - scrollbarOffsetY
            }
            scrollbarHeight = scrollbarHeight.coerceAtLeast(1f)  // Evita scrollbar com altura zero

            drawRoundRect(
                color = Color(0xFF5800D6),
                topLeft = Offset(size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                cornerRadius = CornerRadius(x = width.toPx() / 2, y = width.toPx() / 2),
                alpha = alpha
            )
        }
    })
}