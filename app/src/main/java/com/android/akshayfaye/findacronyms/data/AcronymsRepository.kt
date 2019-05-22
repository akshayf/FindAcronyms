package com.android.akshayfaye.findacronyms.data

import androidx.lifecycle.MutableLiveData
import com.android.akshayfaye.findacronyms.network.AcronymsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AcronymsRepository(val apiService: AcronymsApiService){

    companion object{

        @Volatile private var instance: AcronymsRepository? = null

        fun getInstance(apiService: AcronymsApiService) =
                instance ?: synchronized(this){
                    instance
                        ?: AcronymsRepository(apiService).also { instance = it }
                }
    }

    fun getFullFormsForAcronyms(sf: String, lf: String) : MutableLiveData<AcronymsData>{

        val fullFormData = MutableLiveData<AcronymsData>()

        apiService.getFullFormsForAcronyms(sf, lf).enqueue(object : Callback<AcronymsData> {
            override fun onResponse(
                call: Call<AcronymsData>,
                response: Response<AcronymsData>
            ) {
                if (response.isSuccessful()) {
                    fullFormData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<AcronymsData>, t: Throwable) {
                fullFormData.setValue(null)
            }
        })
        return fullFormData
    }

}