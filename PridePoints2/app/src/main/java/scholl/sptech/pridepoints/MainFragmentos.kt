package scholl.sptech.pridepoints

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(){
    Text("Aqui é a home")
}
@Composable
fun MapScreen(value: String) {
    Text("Aqui precisa ser a tela do mapa: ${value}")
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

