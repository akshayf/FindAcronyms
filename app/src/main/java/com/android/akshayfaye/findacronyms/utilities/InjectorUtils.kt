package com.android.akshayfaye.findacronyms.utilities

import android.content.Context
import com.android.akshayfaye.findacronyms.network.AcronymsApiService
import com.android.akshayfaye.findacronyms.network.RetrofitClient
import com.android.akshayfaye.findacronyms.data.AcronymsRepository
import com.android.akshayfaye.findacronyms.ui.AcronymsViewModelFactory

object InjectorUtils {

    fun provideAcronymsViewModelFactory(context : Context) : AcronymsViewModelFactory {

        val acronymsApiService  = RetrofitClient.getRetrofitClient(context).create(
            AcronymsApiService::class.java)
        val acronymsRepository = AcronymsRepository.getInstance(acronymsApiService)
        return AcronymsViewModelFactory(acronymsRepository)
    }
}