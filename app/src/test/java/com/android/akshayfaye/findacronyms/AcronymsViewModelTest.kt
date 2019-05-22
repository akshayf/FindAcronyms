package com.android.akshayfaye.findacronyms

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.android.akshayfaye.findacronyms.data.AcronymsData
import com.android.akshayfaye.findacronyms.data.AcronymsRepository
import com.android.akshayfaye.findacronyms.ui.AcronymsViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AcronymsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var acronymsRepository: AcronymsRepository

    lateinit var mainViewModel: AcronymsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mainViewModel = AcronymsViewModel(this.acronymsRepository)
    }

    @Test
    fun fetchFullForms_positiveResponse(){

        // Mock API response
        Mockito.`when`(this.acronymsRepository.getFullFormsForAcronyms(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer MutableLiveData(ArgumentMatchers.anyList<AcronymsData>())
        }

        val fullFormData = this.mainViewModel.getFullForms(ArgumentMatchers.anyString())

        Assert.assertNotNull(fullFormData)
        Assert.assertEquals(fullFormData.value, ArgumentMatchers.anyList<AcronymsData>())
    }
}