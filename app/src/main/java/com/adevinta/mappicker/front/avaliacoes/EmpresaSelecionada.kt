package com.adevinta.mappicker.front.avaliacao

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.adevinta.mappicker.R
import com.adevinta.mappicker.classes.ViewModel.EmpresaViewModel
import com.adevinta.mappicker.classes.entidades.EmpresaFullDTO
import com.adevinta.mappicker.ui.theme.pridepointsTheme



class EmpresaSelecionada : ComponentActivity() {
    private val viewModel: EmpresaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val empresaId = intent.getLongExtra("EMPRESA_ID", -1)
        if (empresaId != -1L) {
            // Usando o ID para buscar os detalhes da empresa
            viewModel.buscarEmpresaPorId(empresaId)
        } else {
            // Handle error: invalid empresaId
            Toast.makeText(this, "ID da empresa inválido", Toast.LENGTH_SHORT).show()
        }
        //  val empresaId = intent.getLongExtra("empresaId", -1)  // Supondo que você passe o ID através de Intent
        viewModel.buscarEmpresaPorId(1)
        setContent {
            pridepointsTheme {
                EmpresaScreen(viewModel)
            }
        }
    }
}

@Composable
fun EmpresaScreen(viewModel: EmpresaViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        val empresa by viewModel.empresa.observeAsState()
        val erroApi by viewModel.erroApi.observeAsState("")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6200EE))
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                empresa?.let {
//                    Text(
//                        text = it.nome,  // Supondo que EmpresaFullDTO tem um campo 'nome'
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp
//                    )
//                    Text(
//                        text = "${it.rating} ★★★★★",  // Supondo que EmpresaFullDTO tem um campo 'rating'
//                        color = Color.Yellow,
//                        fontSize = 16.sp
//                    )
//                    Text(
//                        text = it.descricao,  // Supondo que EmpresaFullDTO tem um campo 'descricao'
//                        color = Color.White,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(top = 4.dp)
//                    )
                    // Continue ajustando os demais campos

                Image(
                    painter = painterResource(R.mipmap.persona),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Sucos da Vandinha",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "4.9 ★★★★★",
                        color = Color.Yellow,
                        fontSize = 16.sp
                    )
                }
            }
            Text(
                text = "Banca de diferentes sabores de sucos, para atender diferentes tipos de públicos...",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Divider(color = Color.Black, thickness = 2.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Sobre", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(
                text = "Nossa empresa destaca no mercado de sucos naturais, oferecendo uma ampla variedade de opções saudáveis e saborosas...",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

            Text(
                "Localizado em: Av. Paulista",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                "Horário de funcionamento: 18:00h - 05:00h",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Button(
            onClick = { /* TODO: Handle click */ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Ver avaliações", color = Color.White)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    pridepointsTheme {
        EmpresaScreen(EmpresaViewModel().apply {
            empresa.value = EmpresaFullDTO(
                id = 1,
                nomeFantasia = "Sucos da Vandinhaaaaa",
                cnpj = "00.000.000/0001-00",
                cep = "01311-200",
                numero = 123,
                cidade = "São Paulo",
                estado = "SP",
                dono = "Vandinha"
            )
        })
    }
}
