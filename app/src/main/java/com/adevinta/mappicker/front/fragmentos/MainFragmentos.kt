package com.adevinta.mappicker.front.fragmentos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adevinta.mappicker.MainActivity
import com.adevinta.mappicker.MainView
import com.adevinta.mappicker.front.avaliacoes.TelaAvaliacao
import com.adevinta.mappicker.front.eventosregiao.HomeEventos
import com.adevinta.mappicker.front.perfilusuarios.TelaPerfilConfiguracao
import com.adevinta.mappicker.menuitens.avaliacoes.MinhasAvaliacoes

@Composable
fun HomeScreen(){
    val text = buildAnnotatedString  {
        append("Eventos na Região ")
        withStyle(style = SpanStyle(color = Color(0xFF5800D6))) {
            append("Paulista!")
        }
    }

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = text,
            fontSize = 22.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        HomeEventos()
    }


}
@Composable
fun MapScreen() {

MainView()
}

@Composable
fun MyReviewScreen(){
    MinhasAvaliacoes()
}

//Apagar após a tela de perfil ser feita
@Composable
fun ProfileScreen(){
    TelaPerfilConfiguracao()
}


