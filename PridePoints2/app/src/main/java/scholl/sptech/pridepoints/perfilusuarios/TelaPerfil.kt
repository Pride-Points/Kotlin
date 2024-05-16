package scholl.sptech.pridepoints.perfilusuarios
import android.content.Context
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


@Composable
fun TelaPerfil(modifier: Modifier = Modifier, perfilViewModel: PerfilViewModel = PerfilViewModel()) {
    val context = LocalContext.current

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
                        .clickable { perfilViewModel.postUserProfile(getUserIdFromPreferences(context), "https://unsplash.com/pt-br/fotografias/uma-pessoa-segurando-uma-bandeira-do-arco-iris-na-mao-kqfA8AMt4tI", context ) }
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
                        .clickable { perfilViewModel.postUserProfile(getUserIdFromPreferences(context), "https://empoderadxs.com.br/wp-content/uploads/2020/08/progress-pride-flag.jpg", context ) }
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
                        .clickable { perfilViewModel.postUserProfile(getUserIdFromPreferences(context), "https://static.wikia.nocookie.net/identidades/images/f/fd/Bandeira_l%C3%A9sbica_sunset.png/revision/latest?cb=20220810191743&path-prefix=pt-br", context ) }
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
                        .clickable { perfilViewModel.postUserProfile(getUserIdFromPreferences(context), "https://pbs.twimg.com/media/EZbWtuXXkAE4bS_.jpg", context ) }
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
                        .clickable { perfilViewModel.postUserProfile(getUserIdFromPreferences(context), "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Bisexual_Pride_Flag.svg/300px-Bisexual_Pride_Flag.svg.png", context ) }
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
                        .clickable { perfilViewModel.postUserProfile(getUserIdFromPreferences(context), "https://www.doistercos.com.br/wp-content/uploads/2023/11/bandeiratrans.jpg", context ) }
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

fun getUserIdFromPreferences(context: Context): Long {
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val userIdString = sharedPreferences.getString("USER_ID", "")
    return userIdString?.toLongOrNull() ?: -1L  // Retorna -1 se não for possível converter
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilPreview() {
    PridePointsTheme {
        TelaPerfil()
    }
}