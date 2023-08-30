package com.example.prepaidcardsdk.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor():ViewModel() {
    val commingSoonSheet: MutableState<Boolean> = mutableStateOf(false)
    val convenienceSheet= mutableStateOf(false)
    val ammount= mutableStateOf("")
    val note= mutableStateOf("")
    val transactionType= mutableStateListOf<String>("IMPS")
    val transactionSelected= mutableStateOf("")
    val ammountOptions=mutableStateListOf<String>("500","1000","2000")
    val ammountSelected= mutableStateOf("")




}