package com.adevinta.mappicker.front

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.adevinta.mappicker.classes.DataStorage.DataStoreManager
import com.adevinta.mappicker.classes.ViewModel.LoginViewModel
import com.adevinta.mappicker.front.fragmentos.HomeScreen
import com.adevinta.mappicker.front.fragmentos.MainFragmentos
import com.adevinta.mappicker.front.fragmentos.MapScreen
import com.adevinta.mappicker.front.fragmentos.MenuItem
import com.adevinta.mappicker.front.fragmentos.MyReviewScreen
import com.adevinta.mappicker.front.fragmentos.ProfileScreen
import com.adevinta.mappicker.ui.theme.pridepointsTheme
import com.adevinta.mappicker.R



class TelaInicialUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pridepointsTheme {
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
    val selectedIndex = remember { mutableStateOf(0) }

    val menuItems = remember {
        mutableStateListOf(
            MenuItem(R.mipmap.home_selecionado, R.mipmap.home_menu, isSelected = false),
            MenuItem(R.mipmap.sinalizador_selecionado, R.mipmap.sinalizador_menu, isSelected = false),
            MenuItem(R.mipmap.estrela_selecionado, R.mipmap.estrela_menu, isSelected = false)
        )
    }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Imagem aqui
            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(id = R.mipmap.logo_header),
                contentDescription = "Bandeira PP",
                modifier = Modifier
                    .size(25.dp)
                    .clickable { navController.navigate("home") }

            )

            //Será buscado pela API?
            Spacer(modifier = Modifier.weight(2f))
            Text(text = "Av. Paulista", Modifier.padding(vertical = 15.dp))
            Spacer(modifier = Modifier.weight(1.5f))

            //AQUI PRECISA MANDAR PARA A TELA DE PERFIL
            TextButton(onClick = {
                navController.navigate("PROFILE")
            }) {
                UserImage()
            }
            Spacer(modifier = Modifier.weight(0.5f))


        }
        Divider(
            color = Color(android.graphics.Color.parseColor("#5800D6")),
            thickness = 1.dp
        )

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = MainFragmentos.TELA1.name
        ) {
            composable(MainFragmentos.TELA1.name) {
                HomeScreen()
            }
            composable(MainFragmentos.TELA2.name) {
                MapScreen()
            }
            composable(MainFragmentos.TELA3.name) {
                MyReviewScreen()
            }
            composable("PROFILE") {
                ProfileScreen()
            }
            composable("home") {
                HomeScreen()
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
            /*MainFragmentos.values().forEach {

                TextButton(modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(it.name)}
                ) {
                    // importar do androidx.compose...
                     Image(
                         painter = painterResource(id = it.imagem),
                         contentDescription = it.descricao,
                         modifier = Modifier.size(40.dp))
                }

            }*/

            menuItems.forEachIndexed { index, item ->
                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedIndex.value = index
                        navController.navigate(MainFragmentos.values()[index].name)
                    }
                ) {
                    Image(
                        painter = painterResource(id = if (index == selectedIndex.value) item.naoSelecionado else item.itemSelecionado),
                        contentDescription = MainFragmentos.values()[index].descricao,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }


        }
    }
}

@Composable
fun UserImage(loginViewModel: LoginViewModel = LoginViewModel()) {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)
    loginViewModel.getImgUser(context)

    // Coletar a URL da imagem de perfil do DataStore
    val imageUrl by dataStoreManager.userImageUrl.collectAsState(initial = null)

    // Escolher entre a imagem de perfil ou uma imagem padrão
    val imagePainter = if (imageUrl.isNullOrEmpty()) {
        painterResource(R.mipmap.avatar)  // Imagem padrão
    } else {
        rememberImagePainter(imageUrl)  // Carregar imagem com Coil
    }

    // Exibir a imagem
    Image(
        painter = imagePainter,
        contentDescription = "Imagem de perfil",
        modifier = Modifier
            .size(50.dp)  // Define o tamanho da imagem (ajuste conforme necessário)
            .clip(CircleShape)  // Corta a imagem em forma de círculo
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    pridepointsTheme {
        Tela(rememberNavController())
    }
}