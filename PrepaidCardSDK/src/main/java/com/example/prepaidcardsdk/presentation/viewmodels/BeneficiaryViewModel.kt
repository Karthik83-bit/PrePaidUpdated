package com.example.prepaidcardsdk.presentation.viewmodels

import android.app.Person
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.example.prepaidcard.screens.ViewModel
import com.example.prepaidcardsdk.components.NewBene
import com.example.prepaidcardsdk.data.model.resp.BeneNameResp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class BeneficiaryViewModel @Inject constructor():ViewModel() {

   val list = mutableStateListOf<BeneNameResp>(BeneNameResp("PRATIK MOHANTY","State Bank of India","xxxx xxxx xxxx 1234"), BeneNameResp("SUBHASHREE PANDA", "AXIS BANK", "xxxx xxxx xxxx 5678"))

}