package com.android.akshayfaye.findacronyms.network

import com.android.akshayfaye.findacronyms.data.AcronymsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiService interface for initiating server calls
 */
interface AcronymsApiService {

    @GET("dictionary.py")
    fun getFullFormsForAcronyms(@Query("sf") sf: String): Call<List<AcronymsData>>?
}