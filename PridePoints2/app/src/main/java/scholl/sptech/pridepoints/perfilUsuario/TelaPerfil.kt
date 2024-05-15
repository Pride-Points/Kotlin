package scholl.sptech.pridepoints.perfilUsuario


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import scholl.sptech.pridepoints.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TelaPerfil(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Topo da tela
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.mipmap.seta_esquerda),
                contentDescription = "Back",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Ícones de Perfil",
                modifier = Modifier.padding(vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color(0xFF5800D6))
        )

        Spacer(modifier = Modifier.height(65.dp))


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
                    modifier = Modifier.size(148.dp)
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
                    modifier = Modifier.size(148.dp)
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
                    modifier = Modifier.size(148.dp)
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
                    modifier = Modifier.size(148.dp)
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
                    modifier = Modifier.size(148.dp)
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
                    modifier = Modifier.size(148.dp)
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

@Preview(showBackground = true)
@Composable
fun TelaPerfilPreview() {
    PridePointsTheme {
        TelaPerfil("Android")
    }
}