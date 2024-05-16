package scholl.sptech.pridepoints.perfilusuarios

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import scholl.sptech.pridepoints.api.RetrofitService

class PerfilViewModel: ViewModel() {


    val api = RetrofitService.getApiPridePointsService()
    val erroApi = MutableLiveData("")


    fun postUserProfile(idUser: Long, profileData: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Assume postUserProfile is a method in your Retrofit interface
                val profileDataObj = ImagemPerfil(profileData)
                val response = api.postUserImage(idUser,profileDataObj)
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

}