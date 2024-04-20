package scholl.sptech.pridepoints.fragmentos

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import scholl.sptech.pridepoints.menuitens.avaliacoes.MinhasAvaliacoes

@Composable
fun HomeScreen(){
    Text("Aqui é a home")
}
@Composable
fun MapScreen() {
    Text("Aqui precisa ser a tela do mapa")
}

@Composable
fun MyReviewScreen(){
    MinhasAvaliacoes()
}

//Apagar após a tela de perfil ser feita
@Composable
fun ProfileScreen(){
    Text("Aqui precisa ser o perfil")
}

