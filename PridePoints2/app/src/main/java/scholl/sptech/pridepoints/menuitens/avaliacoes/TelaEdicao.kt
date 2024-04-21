package scholl.sptech.pridepoints.menuitens.avaliacoes

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import scholl.sptech.pridepoints.R
import scholl.sptech.pridepoints.menuitens.RetrofitMock
import scholl.sptech.pridepoints.ui.theme.PridePointsTheme

class TelaEdicao : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //aqui recuperamos os dados enviados da tela anterior
        //esta operação DEVE E TEM que ser feita neste local (onCreate)
        val extras = intent.extras

        setContent {
            PridePointsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaEdicao(extras, this)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
//o nome é extras mas o tipo é bundle
fun TelaEdicao(extras: Bundle?, activity: Activity) {


    val text = buildAnnotatedString {
        append("Como foi sua ")
        withStyle(
            style = SpanStyle(
                color = Color(0xFF5800D6),

                )
        ) {
            append("experiência")
        }
        append("?")

    }
    val aval = extras?.getSerializable("itemSelecionado", Avaliacoes::class.java)

    //API
    val (novaEdit, novaEditSetter) = remember {
        mutableStateOf(Avaliacoes(aval!!.id, aval.loja))
    }
    val msgCadastro = remember {
        mutableStateOf(false)
    }


    val totalEstrelas = 5
    var estrelasSelecionadas by remember {
        mutableStateOf(aval!!.estrelas)
    }

    //TESTAR
    var selectedStarIndex by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Imagem aqui
            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(id = R.mipmap.arrow_left_voltar),
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        activity.finish()
                    }
            )

            Spacer(modifier = Modifier.weight(2f))
            Text(text = "Edição", Modifier.padding(vertical = 15.dp))
            Spacer(modifier = Modifier.weight(3f))

        }
        Divider(
            color = Color(android.graphics.Color.parseColor("#5800D6")),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = text, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            (1..totalEstrelas).forEach { index ->
                Image(
                    painter = painterResource(
                        id = if (index <= estrelasSelecionadas!!) R.mipmap.estrela_amarela else R.mipmap.estrela_cinza
                    ),
                    contentDescription = if (index <= estrelasSelecionadas!!) "Estrela Amarela" else "Estrela Cinza",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            estrelasSelecionadas = index
                            novaEditSetter(novaEdit.copy(estrelas = index))
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Como se sentiu durante o tempo permanecido no estabelecimento?",
            modifier = Modifier.fillMaxWidth(0.7f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.Center) {
            val color = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000)
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(modifier = Modifier
                    .size(120.dp, 40.dp)
                    .border(
                        1.dp, Color.Black, RoundedCornerShape(10.dp)
                    ),
                    colors = color,
                    onClick = {
                        novaEditSetter(novaEdit.copy(sentimento = "Amade"))
                    }) {
                    Text(text = "Amade")
                }

                Button(modifier = Modifier
                    .size(120.dp, 40.dp)
                    .border(
                        1.dp, Color.Black, RoundedCornerShape(10.dp)
                    ),

                    colors = color, onClick = {
                        novaEditSetter(novaEdit.copy(sentimento = "Amade"))
                    }) {
                    Text(text = "Brave")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(modifier = Modifier
                    .size(120.dp, 40.dp)
                    .border(
                        1.dp, Color.Black, RoundedCornerShape(10.dp)
                    ),

                    colors = color, onClick = {
                        novaEditSetter(novaEdit.copy(sentimento = "Amade"))
                    }) {
                    Text(text = "Acolhide")
                }

                Button(modifier = Modifier
                    .size(120.dp, 40.dp)
                    .border(
                        1.dp, Color.Black, RoundedCornerShape(10.dp)
                    ),

                    colors = color, onClick = {
                        novaEditSetter(novaEdit.copy(sentimento = "Amade"))
                    }) {
                    Text(text = "Frustrade")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(modifier = Modifier
                    .size(120.dp, 40.dp)
                    .border(
                        1.dp, Color.Black, RoundedCornerShape(10.dp)
                    ),

                    colors = color, onClick = {
                        novaEditSetter(novaEdit.copy(sentimento = "Amade"))
                    }) {
                    Text(text = "Feliz")
                }

                Button(modifier = Modifier
                    .size(120.dp, 40.dp)
                    .border(
                        1.dp, Color.Black, RoundedCornerShape(10.dp)
                    ),

                    colors = color, onClick = {
                        novaEditSetter(novaEdit.copy(sentimento = "Amade"))
                    }) {
                    Text(text = "Humilhade")
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(label = { Text(text = "Comentário") },
            value = novaEdit.comentario.toString(),
            onValueChange = { novaEditSetter(novaEdit.copy(comentario = it)) }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(modifier = Modifier
            .size(120.dp, 60.dp),
            onClick = {
                val apiAval = RetrofitMock.getApiAvaliacoes()
                apiAval.put(aval!!.id, novaEdit).enqueue(object : Callback<Avaliacoes> {
                    override fun onResponse(
                        call: Call<Avaliacoes>,
                        response: Response<Avaliacoes>
                    ) {
                        if (response.isSuccessful) {
                            val resposta = response.body()
                            if (resposta != null) {
                                activity.finish()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Avaliacoes>, t: Throwable) {

                    }

                })
            }) {
            Text(text = "Avaliar", fontSize = 20.sp)

        }

    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    PridePointsTheme {
        TelaEdicao(null, Activity())
    }
}
