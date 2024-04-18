package scholl.sptech.pridepoints.menuitens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Response

@Composable
fun MinhasAvaliacoes() {
    val avals = remember {
        mutableStateListOf<Avaliacoes>()
    }

    val apiAval = RetrofitMock.getApiAvaliacoes()

    val get = apiAval.get()

    get.enqueue(object : retrofit2.Callback<List<Avaliacoes>> {
        override fun onResponse(
            call: Call<List<Avaliacoes>>,
            response: Response<List<Avaliacoes>>
        ) {
            if (response.isSuccessful) {
                val lista = response.body()
                if (lista != null) {
                    avals.clear()
                    avals.addAll(lista)
                }
            }
        }

        override fun onFailure(call: Call<List<Avaliacoes>>, t: Throwable) {

        }

    })

    Column {


        if (avals.isEmpty()) {
            Text(text = "Faça sua primeira avaliação para ela aparecer aqui :)")
        } else {
            Text("Suas avaliações!")

            LazyColumn(Modifier.height(720.dp)) {
                items(items = avals) { aval ->
                    var isExpanded: Boolean by remember {
                        mutableStateOf(false)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                            .background(Color.White)
                    ) {
                        Column(modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {

                                Text(text = "${aval.loja}",
                                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = "estrelas aqui")

                            }
                            Text(
                                text = if (isExpanded) "${aval.comentario}" else "${
                                    aval.comentario!!.take(100)}..."
                            )
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {

                                if (!isExpanded && aval.comentario!!.length > 100) {

                                    Button(onClick = { isExpanded = true }) {
                                        Text("Ver mais")
                                    }
                                }
                                if(isExpanded) {

                                    Button(onClick = { isExpanded = false }) {
                                        Text("Ver menos")
                                    }
                                }

                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = "edit e delete")
                            }
                        }

                    }
                    Spacer(modifier = Modifier.weight(1f))
                }


            }
        }
    }
}