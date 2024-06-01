package com.adevinta.mappicker.classes.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.entidades.EmpresaFullDTO

class EmpresaViewModel : ViewModel() {
    val empresa = MutableLiveData<EmpresaFullDTO?>()
    val erroApi = MutableLiveData("")

    private val api = RetrofitService.getApipridepointsService()

    fun buscarEmpresaPorId(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.buscarPorId(id)
                if (response.isSuccessful) {
                    empresa.postValue(response.body())
                } else {
                    erroApi.postValue("Erro ao buscar dados da empresa: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                erroApi.postValue("Erro de conexão: ${e.message}")
            }
        }
    }
}
