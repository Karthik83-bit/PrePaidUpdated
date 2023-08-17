package com.example.prepaidcardsdk.domain.usecases

import com.example.prepaidcardsdk.data.model.SetPinResponse
import com.example.prepaidcardsdk.domain.repo.Repository
import com.example.prepaidcardsdk.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetPinUseCase@Inject constructor(val repository: Repository) {
    fun invoke(): Flow<NetworkResponse<SetPinResponse>> {
        return repository.setPin()
    }
}