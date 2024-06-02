package com.adevinta.mappicker.front.perfilusuarios

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.adevinta.mappicker.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adevinta.mappicker.classes.DataStorage.DataStoreManager
import com.adevinta.mappicker.ui.theme.pridepointsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.adevinta.mappicker.classes.ViewModel.PerfilViewModel
import com.adevinta.mappicker.front.telasInscricao.Home

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

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.text_defina_imagem_perfil),
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
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { perfilViewModel.postUserProfile("https://i.imgur.com/FRyol1v.png") }
                )
                Text(
                    text = stringResource(R.string.text_encontre_se),
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
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { perfilViewModel.postUserProfile("https://i.imgur.com/1Xm10Ti.png") }
                )
                Text(
                    text = stringResource(R.string.text_orgulho),
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
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { perfilViewModel.postUserProfile("https://i.imgur.com/FIGlvZK.png") }
                )
                Text(
                    text = stringResource(R.string.text_lesbica),
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
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { perfilViewModel.postUserProfile("https://i.imgur.com/UyrpHAK.png") }
                )
                Text(
                    text = stringResource(R.string.text_gay),
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
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { perfilViewModel.postUserProfile("https://i.imgur.com/AsykGMW.png") }
                )
                Text(
                    text = stringResource(R.string.text_bissexual),
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
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { perfilViewModel.postUserProfile("https://i.imgur.com/K2eWReM.png") }
                )
                Text(
                    text = stringResource(R.string.text_transexual),
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
                contentDescription = stringResource(R.string.content_description_sair),
                modifier = Modifier
                    .padding(start = 16.dp, top = 20.dp)
                    .size(75.dp)
                    .clickable {
                        CoroutineScope(Dispatchers.IO).launch {
                            dataStoreManager.clearData()
                            context.startActivity(Intent(context, Home::class.java))
                        }
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.mipmap.logo_pride),
                contentDescription = stringResource(R.string.content_description_logo_pride),
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
    pridepointsTheme {
        TelaPerfilConfiguracao()
    }
}
