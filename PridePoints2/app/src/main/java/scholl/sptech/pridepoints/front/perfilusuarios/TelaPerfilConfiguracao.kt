package scholl.sptech.pridepoints.front.perfilusuarios
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import scholl.sptech.pridepoints.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import scholl.sptech.pridepoints.classes.ViewModel.PerfilViewModel
import scholl.sptech.pridepoints.front.telasInscricao.Home
import scholl.sptech.pridepoints.storage.DataStoreManager


@Composable
fun TelaPerfilConfiguracao(modifier: Modifier = Modifier, perfilViewModel: PerfilViewModel = PerfilViewModel(dataStoreManager = DataStoreManager(LocalContext.current))) {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center ){
            Text(
                text = "Defina sua imagem de perfil",
                modifier = Modifier
                    .padding(bottom = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

// Imagens e textos
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.descubra),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .clickable { perfilViewModel.postUserProfile( "https://i.imgur.com/FRyol1v.png") }
                )
                Text(
                    text = "Encontre-se",
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(20.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.orgulho),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .clickable { perfilViewModel.postUserProfile( "https://i.imgur.com/1Xm10Ti.png") }
                )
                Text(
                    text = "Orgulho",
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.lesbica),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .clickable { perfilViewModel.postUserProfile( "https://i.imgur.com/FIGlvZK.png") }
                )
                Text(
                    text = "Lésbisca",
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(20.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.gay),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .clickable { perfilViewModel.postUserProfile( "https://i.imgur.com/UyrpHAK.png") }
                )
                Text(
                    text = "Gay",
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.bissexual),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .clickable { perfilViewModel.postUserProfile( "https://i.imgur.com/AsykGMW.png") }
                )
                Text(
                    text = "Bissexual",
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(20.dp))


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.transexual),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                        .clickable { perfilViewModel.postUserProfile( "https://i.imgur.com/K2eWReM.png") }
                )
                Text(
                    text = "Transexual",
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))


        // Linha cinza
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)
        )

        // Imagens no rodapé
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.mipmap.sair),
                contentDescription = "Left Image",
                modifier = Modifier
                    .padding(start = 16.dp, top = 20.dp)
                    .size(75.dp)
                    .clickable {
                        CoroutineScope(Dispatchers.IO).launch {
                            context.startActivity(Intent(context, Home::class.java))
                        }
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.mipmap.logo_pride),
                contentDescription = "Right Image",
                modifier = Modifier
                    .padding(end = 16.dp, top = 20.dp)
                    .size(75.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TelaPerfilPreview() {
    PridePointsTheme {
        TelaPerfilConfiguracao()
    }
}