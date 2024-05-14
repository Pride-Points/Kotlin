package scholl.sptech.pridepoints.avaliacoes

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import scholl.sptech.pridepoints.api.RetrofitService

class AvaliacaoViewModel : ViewModel() {

    val avaliacoes = MutableLiveData(SnapshotStateList<Avaliacao>())

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