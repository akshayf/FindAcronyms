package com.android.akshayfaye.findacronyms.utilities

object Utils {

    fun validateAcronymsString(acronymsString : String) : Boolean{

        val regex = "[A-Z]+".toRegex()
        return regex.containsMatchIn(acronymsString)
    }
}