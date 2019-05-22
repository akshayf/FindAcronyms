package com.android.akshayfaye.findacronyms

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.android.akshayfaye.findacronyms.data.AcronymsData
import com.android.akshayfaye.findacronyms.data.AcronymsRepository
import com.android.akshayfaye.findacronyms.network.AcronymsApiService
import com.android.akshayfaye.findacronyms.ui.AcronymsViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AcronymsRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: AcronymsApiService

    lateinit var acronymsRepository: AcronymsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.acronymsRepository = AcronymsRepository(apiService)
    }

    @Test
    fun fetchFullForms_positiveResponse(){

        // Mock API response
        Mockito.`when`(this.apiService.getFullFormsForAcronyms(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer MutableLiveData(ArgumentMatchers.anyList<AcronymsData>())
        }

        val mutableLiveData = this.acronymsRepository.getFullFormsForAcronyms(ArgumentMatchers.anyString())

        assertNotNull(mutableLiveData)
    }
}
