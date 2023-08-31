package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.prepaidcardsdk.data.model.resp.BeneNameResp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeneficiaryViewModel @Inject constructor(): ViewModel() {

   val list = mutableStateListOf<BeneNameResp>(
      BeneNameResp("PRATIK MOHANTY","State Bank of India","123456781234"),
      BeneNameResp("SUBHASHREE PANDA", "AXIS BANK", "987445765678")
   )

   var enterBeneisClicked: MutableState<Boolean> = mutableStateOf(true); private set
   var enterBene: MutableState<String> = mutableStateOf(""); private set
   var enterBeneError: MutableState<Boolean> = mutableStateOf(false); private set
   var enterBeneErrorMessage: MutableState<String> = mutableStateOf(""); private set

   var enterBankisClicked: MutableState<Boolean> = mutableStateOf(true); private set
   var enterBank: MutableState<String> = mutableStateOf("");private set
   var enterBankError: MutableState<Boolean> = mutableStateOf(false); private set
   var enterBankErrorMessage: MutableState<String> = mutableStateOf(""); private set

   var accNumClick: MutableState<Boolean> = mutableStateOf(true); private set
   var accNum : MutableState<String> = mutableStateOf(""); private set
   var accNumError: MutableState<Boolean> = mutableStateOf(false); private set
   var accNumErrorMessage: MutableState<String> = mutableStateOf(""); private set

   var reEnterAccNumClick: MutableState<Boolean> = mutableStateOf(true); private set
   var reEnterAccNum:MutableState<String> = mutableStateOf(""); private set
   var reEnterError: MutableState<Boolean> = mutableStateOf(false); private set
   var reEnterErrorMessage: MutableState<String> = mutableStateOf(""); private set

   var ifscNumClick: MutableState<Boolean> = mutableStateOf(true); private set
   var ifscNum: MutableState<String> = mutableStateOf(""); private set
   var ifscError: MutableState<Boolean> = mutableStateOf(false); private set
   var ifscErrorMessage: MutableState<String> = mutableStateOf(""); private set

}