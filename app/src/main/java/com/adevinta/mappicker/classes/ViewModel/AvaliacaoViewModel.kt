package com.adevinta.mappicker.avaliacoes

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.DataStorage.DataStoreManager
import com.adevinta.mappicker.classes.entidades.AvaliacaoCriacaoDTO
import com.adevinta.mappicker.classes.entidades.AvaliacaoDTO

class AvaliacaoViewModel : ViewModel() {

    val avaliacoes = MutableLiveData<List<AvaliacaoDTO>>()
    val erroApi = MutableLiveData<String>()

    private val api = RetrofitService.getApipridepointsService()

    fun carregarAvaliacoesDaEmpresa(context: Context, empresaId: Long) {
        Log.d("AD", "Empresa ID recebido: $empresaId") // Log para o ID da empresa
        val dataStoreManager = DataStoreManager(context)

        viewModelScope.launch {
            try {
                val token = dataStoreManager.token.first()
                if (token != null) {
                    val response = api.getAvaliacoesByEmpresaId2(empresaId.toInt(), "Bearer $token")
                    if (response.isSuccessful) {
                        val avaliacoesList = response.body()
                        if (avaliacoesList != null) {
                            avaliacoes.postValue(avaliacoesList!!)
                        } else {
                            Log.e("api", "A lista de avaliações é nula")
                            avaliacoes.postValue(emptyList())
                        }
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Erro desconhecido"
                        Log.e("api", "Erro ao carregar avaliações da empresa: $errorMessage")
                        erroApi.postValue(errorMessage)
                        avaliacoes.postValue(emptyList()) // Adicionado para garantir que a lista vazia seja tratada
                    }
                } else {
                    Log.e("api", "Token não encontrado")
                    erroApi.postValue("Token não encontrado")
                    avaliacoes.postValue(emptyList()) // Adicionado para garantir que a lista vazia seja tratada
                }
            } catch (e: Exception) {
                Log.e("api", "Exceção ao carregar avaliações da empresa: ${e.message}")
                erroApi.postValue(e.message)
                avaliacoes.postValue(emptyList()) // Adicionado para garantir que a lista vazia seja tratada
            }
        }
    }


    fun carregarAvaliacoesDoUsuario(context: Context) {
        val dataStoreManager = DataStoreManager(context)

        viewModelScope.launch {
            try {
                val token = dataStoreManager.token.first()
                val idUsuario = dataStoreManager.userId.first()

                if (token != null && idUsuario != null) {
                    val response = api.getAvaliacoesUsuario(idUsuario, "Bearer $token")
                    if (response.isSuccessful) {
                        avaliacoes.postValue(response.body())
                    } else {
                        Log.e("api", "Erro ao carregar avaliações do usuário")
                        erroApi.postValue(response.errorBody()?.string())
                    }
                } else {
                    Log.e("api", "Token ou ID do usuário não encontrados")
                    erroApi.postValue("Token ou ID do usuário não encontrados")
                }
            } catch (e: Exception) {
                Log.e("api", "Exceção ao carregar avaliações do usuário: ${e.message}")
                erroApi.postValue(e.message)
            }
        }
    }


    fun criarAvaliacao(context: Context, avaliacao: AvaliacaoCriacaoDTO, idEmpresa: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val dataStoreManager = DataStoreManager(context)

        viewModelScope.launch {
            try {
                val token = dataStoreManager.token.first()
                val idUsuario = dataStoreManager.userId.first()

                if (token != null && idUsuario != null) {
                    val response = api.postAvaliacao(avaliacao, idEmpresa, idUsuario, "Bearer $token")
                    if (response.isSuccessful) {
                        Log.d("api", "Avaliação criada com sucesso: ${response.body()}")
                        withContext(Dispatchers.Main) {
                            onSuccess()
                        }
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Erro desconhecido"
                        Log.e("api", "Erro ao criar avaliação: $errorMessage")
                        val errorM = idUsuario.toString() + idEmpresa.toString() + avaliacao.toString() + token.toString()
                        Log.e("API", errorM)
                        withContext(Dispatchers.Main) {
                            onError(errorMessage)
                        }
                    }
                } else {
                    val errorMessage = "Token ou ID do usuário não encontrados"
                    Log.e("api", errorMessage)
                    withContext(Dispatchers.Main) {
                        onError(errorMessage)
                    }
                }
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Erro desconhecido"
                Log.e("api", "Exceção ao criar avaliação: $errorMessage")
                withContext(Dispatchers.Main) {
                    onError(errorMessage)
                }
            }
        }
    }


    fun removerAvaliacao(context: Context, idAvaliacao: Long) {
        val dataStoreManager = DataStoreManager(context)

        viewModelScope.launch {
            try {
                val token = dataStoreManager.token.first()

                if (token != null) {
                    val response = api.deleteAvaliacao(idAvaliacao.toInt(), "Bearer $token")
                    if (response.isSuccessful) {
                        carregarAvaliacoesDoUsuario(context)
                    } else {
                        Log.e("api", "Erro ao deletar avaliação")
                        erroApi.postValue(response.errorBody()?.string() ?: "Erro ao deletar avaliação")
                    }
                } else {
                    Log.e("api", "Token não encontrado")
                    erroApi.postValue("Token não encontrado")
                }
            } catch (e: Exception) {
                Log.e("api", "Erro na API ao deletar avaliação: ${e.message}")
                erroApi.postValue(e.message ?: "Erro desconhecido")
            }
        }
    }
    fun atualizarAvaliacao(context: Context, idAvaliacao: Long, novaAvaliacao: AvaliacaoDTO, idEmpresa: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val dataStoreManager = DataStoreManager(context)

        viewModelScope.launch {
            try {
                val token = dataStoreManager.token.first()
                val idUsuario = dataStoreManager.userId.first()

                if (token != null && idUsuario != null) {
                    val response = api.updateAvaliacao(idAvaliacao.toInt(), idUsuario.toInt(),idEmpresa.toInt(),"Bearer $token", novaAvaliacao )
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            onSuccess()
                        }
                    } else {
                        Log.e("api", "Erro ao atualizar avaliação")
                        withContext(Dispatchers.Main) {
                            onError(response.errorBody()?.string() ?: "Erro ao atualizar avaliação")
                        }
                    }
                } else {
                    Log.e("api", "Token ou ID do usuário não encontrados")
                    withContext(Dispatchers.Main) {
                        onError("Token ou ID do usuário não encontrados")
                    }
                }
            } catch (e: Exception) {
                Log.e("api", "Exceção ao atualizar avaliação: ${e.message}")
                withContext(Dispatchers.Main) {
                    onError(e.message ?: "Erro desconhecido")
                }
            }
        }
    }
    fun recarregarAvaliacoes(context: Context, empresaId: Long) {
        carregarAvaliacoesDaEmpresa(context, empresaId)
    }
}
