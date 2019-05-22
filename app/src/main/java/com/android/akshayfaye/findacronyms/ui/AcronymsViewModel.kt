package com.android.akshayfaye.findacronyms.ui

import androidx.lifecycle.ViewModel
import com.android.akshayfaye.findacronyms.data.AcronymsRepository

/**
 * ViewModel class as interface between View and Repository
 * @param acronymsRepository
 * @return MutableLiveData
 */
class AcronymsViewModel(val acronymsRepository: AcronymsRepository) : ViewModel(){

    fun getFullForms(sf: String)  = acronymsRepository.getFullFormsForAcronyms(sf)

}