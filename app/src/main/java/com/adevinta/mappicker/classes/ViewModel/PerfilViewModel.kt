package com.adevinta.mappicker.classes.ViewModel

import android.content.Context
import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adevinta.mappicker.api.RetrofitService
import com.adevinta.mappicker.classes.entidades.ImagemPerfil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PerfilViewModel: ViewModel() {


    val api = RetrofitService.getApipridepointsService()
    val erroApi = MutableLiveData("")


    fun postUserProfile(idUser: Long, userToken: String, profileData: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Assume postUserProfile is a method in your Retrofit interface
                val profileDataObj = ImagemPerfil(profileData)
                val response = api.patchUserImage(idUser,profileDataObj)
                print(response)
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    val imageUrl = userProfile?.imgUser

                    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
                    sharedPreferences.edit().apply {
                        putString("USER_IMAGE_URL", imageUrl)
                        apply()
                    }
                } else {
                    Log.e("api", "erro no post")
                    erroApi.postValue(response.errorBody()?.string() ?: "Unknown error")
                }
            } catch (e: Exception) {
                Log.e("api", "Exception in post! ${e.message}")
                erroApi.postValue(e.message ?: "Unknown error")
            }
        }

    }
    private val _imageResult = MutableLiveData<String?>()
    val imageResult: LiveData<String?> = _imageResult

    fun fetchUserImage(idUser: Long, token: String) {
        viewModelScope.launch {
            try {
                val apiService = RetrofitService.getApipridepointsService()
                val response = apiService.getUserImage(idUser, "Bearer $token")
                if (response.isSuccessful) {
                    _imageResult.value = response.body()?.imgUser
                } else {
                    _imageResult.value = null
                }
            } catch (e: Exception) {
                _imageResult.value = null
            }
        }
    }
}

