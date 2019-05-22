package com.android.akshayfaye.findacronyms.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.akshayfaye.findacronyms.network.AcronymsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository to communicate between ApiService and Model class
 */
class AcronymsRepository(val apiService: AcronymsApiService){

    val TAG : String = "AcronymsRepository";

    companion object{

        @Volatile private var instance: AcronymsRepository? = null

        fun getInstance(apiService: AcronymsApiService) =
                instance ?: synchronized(this){
                    instance
                        ?: AcronymsRepository(apiService).also { instance = it }
                }
    }

    /**
     * Fetches the response from ApiService and convert it in to MutableLiveData
     * @param sf
     * @return MutableLiveData
     */
    fun getFullFormsForAcronyms(sf: String) : MutableLiveData<List<AcronymsData>>{

        val fullFormData = MutableLiveData<List<AcronymsData>>()
        val call = apiService.getFullFormsForAcronyms(sf)

        call?.enqueue(object : Callback<List<AcronymsData>> {
            override fun onResponse(call: Call<List<AcronymsData>>?, response: Response<List<AcronymsData>>?) {
                fullFormData.postValue(response?.body())
                Log.e(TAG, "Response - " + response);
            }

            override fun onFailure(call: Call<List<AcronymsData>>?, t: Throwable?) {
                fullFormData.value = null
                Log.e(TAG, "Response --- " + null);
            }
        })

        return fullFormData
    }

}