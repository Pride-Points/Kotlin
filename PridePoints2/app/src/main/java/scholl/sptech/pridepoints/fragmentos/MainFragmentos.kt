package scholl.sptech.pridepoints.fragmentos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import scholl.sptech.pridepoints.eventosregiao.HomeEventos

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
    Text("Aqui precisa ser a tela do mapa")
}

@Composable
fun MyReviewScreen(){
    Text("Aqui é a tela de avaliações")
}

//Apagar após a tela de perfil ser feita
@Composable
fun ProfileScreen(){
    Text("Aqui precisa ser o perfil")
}

