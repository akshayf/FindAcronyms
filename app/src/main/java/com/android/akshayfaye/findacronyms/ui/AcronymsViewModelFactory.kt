package com.android.akshayfaye.findacronyms.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.akshayfaye.findacronyms.data.AcronymsRepository

class AcronymsViewModelFactory(private val acronymsRepository : AcronymsRepository)
    : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AcronymsViewModel(acronymsRepository) as T
    }

}