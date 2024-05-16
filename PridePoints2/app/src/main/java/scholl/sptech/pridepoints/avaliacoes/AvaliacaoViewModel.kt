package scholl.sptech.pridepoints.avaliacoes

import android.content.Context
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import scholl.sptech.pridepoints.api.RetrofitService

class AvaliacaoViewModel : ViewModel() {


    val mockAvaliacoes = SnapshotStateList<Avaliacao>().apply {
        add(Avaliacao(id = 1, loja = "Loja A", comentario = "Ótimo serviço!", sentimento = "positivo", estrelas = 5))
        add(Avaliacao(id = 2, loja = "Loja B", comentario = "Poderia ser melhor.", sentimento = "negativo", estrelas = 2))
        add(Avaliacao(id = 3, loja = "Loja C", comentario = "Atendimento razoável.", sentimento = "neutro", estrelas = 3))
        add(Avaliacao(id = 4, loja = "Loja D", comentario = "Excelente qualidade dos produtos!", sentimento = "positivo", estrelas = 4))
        add(Avaliacao(id = 5, loja = "Loja E", comentario = "Não gostei do atendimento.", sentimento = "negativo", estrelas = 1))
    }

    val avaliacoes = MutableLiveData(mockAvaliacoes)

    val api = RetrofitService.getApiPridePointsService()
    val erroApi = MutableLiveData("")

    init {
        carregarTodasAvaliacoes()
    }

    fun carregarTodasAvaliacoes(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.getAvaliacoes()
                if (response.isSuccessful) {
                    avaliacoes.value!!.clear()
                    avaliacoes.value!!.addAll(response.body() ?: emptyList())
                } else {
                    Log.e("api", "erro no get")
                    erroApi.postValue(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                Log.e("api", "Deu ruim no get! ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }

    fun criarAvaliacao(avaliacao: Avaliacao, idUser: Long, idEmpresa: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Presume-se que 'api' é uma instância de um cliente Retrofit configurado
                val response = api.postAvaliacao(avaliacao, idUser, idEmpresa)  // Envia o objeto 'avaliacao' para a API
                if (response.isSuccessful) {
                    // Operações em caso de sucesso, como atualizar a UI ou lista de dados
                    withContext(Dispatchers.Main) {
                        onSuccess()
                    }
                } else {
                    // Trata erro de resposta não bem-sucedida
                    Log.e("api", "Erro ao criar avaliação")
                    withContext(Dispatchers.Main) {
                        onError(response.errorBody()?.string() ?: "Erro desconhecido")
                    }
                }
            } catch (e: Exception) {
                // Trata exceções de chamada de rede ou serialização
                Log.e("api", "Exceção ao criar avaliação: ${e.message}")
                withContext(Dispatchers.Main) {
                    onError(e.message ?: "Erro desconhecido")
                }
            }
        }
    }

    fun getUserIdFromPreferences(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val userIdString = sharedPreferences.getString("USER_ID", "")
        return userIdString?.toLongOrNull() ?: -1L  // Retorna -1 se não for possível converter
    }

    fun removerAvaliacao(idAvaliacao: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.deleteAvaliacao(idAvaliacao)
                if (response.isSuccessful) {
                    carregarTodasAvaliacoes()  // Recarregar as avaliações após a remoção
                } else {
                    Log.e("api", "Erro ao deletar avaliação")
                    erroApi.postValue(response.errorBody()?.string() ?: "Erro ao deletar avaliação")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro na API ao deletar avaliação: ${e.message}")
                erroApi.postValue(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun atualizarAvaliacao(id: Int, novaAvaliacao: Avaliacao, onSuccess: () -> Unit, onError: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.updateAvaliacao(id, novaAvaliacao)
                if (response.isSuccessful) {
                    val updatedAvaliacao = response.body()
                    // Atualize seu LiveData ou estado aqui, se necessário
                    // Notifique a UI sobre a atualização bem sucedida
                    onSuccess()
                } else {
                    Log.e("api", "Erro ao atualizar avaliação")
                    onError(response.errorBody()?.string() ?: "Erro ao atualizar avaliação")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            }
        }
    }
}