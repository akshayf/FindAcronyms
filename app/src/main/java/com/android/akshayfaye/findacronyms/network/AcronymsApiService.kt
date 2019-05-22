package com.android.akshayfaye.findacronyms.network

import com.android.akshayfaye.findacronyms.data.AcronymsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymsApiService {

    @GET("software/acromine/dictionary.py")
    fun getFullFormsForAcronyms(@Query("sf") sf: String,
                       @Query("lf") lf: String): Call<AcronymsData>
}