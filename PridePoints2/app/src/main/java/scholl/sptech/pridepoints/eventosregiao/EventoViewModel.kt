package scholl.sptech.pridepoints.eventosregiao

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import scholl.sptech.pridepoints.api.RetrofitService

class EventoViewModel : ViewModel() {

    val eventos = MutableLiveData(SnapshotStateList<EventosRegiao>())

    val api = RetrofitService.getApiPridePointsService()
    val erroApi = MutableLiveData("")

    init {
        carregarTodos()
    }

    fun carregarTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = api.get()
                if (response.isSuccessful) {
                    eventos.value!!.clear()
                    eventos.value!!.addAll(response.body() ?: emptyList())
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
}