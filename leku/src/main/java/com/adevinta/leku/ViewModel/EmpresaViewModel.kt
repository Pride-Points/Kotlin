package com.adevinta.leku.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.leku.api.EmpresaFullDTO
import com.adevinta.leku.api.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmpresaViewModel : ViewModel() {

    suspend fun fetchEmpresas(): List<EmpresaFullDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                val listaDeEmpresas = RetrofitService.getApipridepointsService().listarEmpresasCompleto()
                listaDeEmpresas
            } catch (e: Exception) {
                // Handle the exception
                null
            }
        }
    }
}
