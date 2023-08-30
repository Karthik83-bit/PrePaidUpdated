package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.prepaidcardsdk.data.model.resp.BeneNameResp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeneficiaryViewModel @Inject constructor(): ViewModel() {

   val list = mutableStateListOf<BeneNameResp>(BeneNameResp("PRATIK MOHANTY","State Bank of India","xxxx xxxx xxxx 1234"), BeneNameResp("SUBHASHREE PANDA", "AXIS BANK", "xxxx xxxx xxxx 5678"))

}