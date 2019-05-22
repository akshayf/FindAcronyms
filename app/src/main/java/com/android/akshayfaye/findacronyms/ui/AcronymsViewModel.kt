package com.android.akshayfaye.findacronyms.ui

import androidx.lifecycle.ViewModel
import com.android.akshayfaye.findacronyms.data.AcronymsRepository

class AcronymsViewModel(val acronymsRepository: AcronymsRepository) : ViewModel(){

    fun getFullForms(sf: String, lf: String)  = acronymsRepository.getFullFormsForAcronyms(sf,lf)

}