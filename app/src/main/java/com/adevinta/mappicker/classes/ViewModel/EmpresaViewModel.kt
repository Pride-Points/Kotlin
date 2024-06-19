package com.adevinta.mappicker.classes.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.entidades.EmpresaFullDTO
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class EmpresaViewModel : ViewModel() {
    val empresa = MutableLiveData<EmpresaFullDTO?>()
    val erroApi = MutableLiveData("")
    private val _empresa = MutableLiveData<EmpresaFullDTO>()
    private val _erroApi = MutableLiveData<String>()
    private val retrofitService = RetrofitService.getApipridepointsService()


    private val _nomeEmpresa = MutableLiveData<String>()
    val nomeEmpresa: LiveData<String> = _nomeEmpresa


    private val api = RetrofitService.getApipridepointsService()

    fun buscarEmpresaPorId(id: Long) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitService.getApipridepointsService()
                val response = apiService.buscarPorId(id)
                if (response.isSuccessful) {
                    _empresa.value = response.body()
                } else {
                    _erroApi.value = response.errorBody()?.string() ?: "Erro desconhecido"
                }
            } catch (e: Exception) {
                Log.e("api", "Exceção ao buscar empresa: ${e.message}")
                _erroApi.value = e.message ?: "Erro desconhecido"
            }
        }
    }
}
