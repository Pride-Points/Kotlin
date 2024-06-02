package com.adevinta.mappicker.classes.ViewModel

import android.content.Context
import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.DataStorage.DataStoreManager
import com.adevinta.mappicker.classes.entidades.ImagemPerfil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.log

class PerfilViewModel(private val dataStoreManager: DataStoreManager): ViewModel() {


    val api = RetrofitService.getApipridepointsService()
    val erroApi = MutableLiveData("")



    fun postUserProfile(profileData: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val token = dataStoreManager.token.first()
            val userId = dataStoreManager.userId.first()

            try {
                // Assume postUserProfile is a method in your Retrofit interface

                if (token != null && userId != null) {
                    val profileDataObj = ImagemPerfil(profileData)
                    val response = api.patchUserImage(userId, profileDataObj)
                    print(response)
                    if (response.isSuccessful) {
                        val userProfile = response.body()
                        val imageUrl = userProfile?.imgUser
                        // Salvar a URL da imagem no DataStore
                        if (imageUrl != null) {
                            dataStoreManager.saveUserImageUrl(imageUrl)
                        }
                    } else {
                        Log.e("api", "erro no post")
                        erroApi.postValue(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            } catch (e: Exception) {
                Log.e("api", "Exception in post! ${e.message}")
                erroApi.postValue(e.message ?: "Unknown error")
            }
        }
    }
}

