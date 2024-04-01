package scholl.sptech.pridepoints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PridePointsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tela(rememberNavController())
                }
            }
        }
    }
}

@Composable
fun Tela(navController: NavHostController, modifier: Modifier = Modifier) {
    val mensagem = remember { mutableStateOf("será?") }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            //Imagem aqui
            Text(text = "BandeiraPride", Modifier.padding(vertical = 15.dp))

            //Será buscado pela API?
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Av. Paulista", Modifier.padding(vertical = 15.dp))
            Spacer(modifier = Modifier.weight(2f))

            //AQUI PRECISA MANDAR PARA A TELA DE PERFIL
            TextButton(onClick = {
                navController.navigate("PROFILE")
            }) {
                Text(text = "teste")
            }

        }
        Divider(color = Color.Gray, thickness = 1.dp)

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = MainFragmentos.TELA1.name
        ) {
            composable(MainFragmentos.TELA1.name) {
                HomeScreen()
            }
            composable(MainFragmentos.TELA2.name) {
                MapScreen(value = "Suco de água")
            }
            composable(MainFragmentos.TELA3.name) {
                MyReviewScreen()
            }
            composable("PROFILE") {
                ProfileScreen()
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        //linha
        Divider(color = Color.Gray, thickness = 1.dp)



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            MainFragmentos.values().forEach {

                TextButton(modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(it.name) }
                ) {
                    // importar do androidx.compose...
                    /* Image(
                         painter = painterResource(id = it.imagem),
                         contentDescription = it.descricao )
                 */
                    Text(text = "ibagem")
                }

                Spacer(modifier = Modifier.width(8.dp))
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PridePointsTheme {
        Tela(rememberNavController())
    }
}