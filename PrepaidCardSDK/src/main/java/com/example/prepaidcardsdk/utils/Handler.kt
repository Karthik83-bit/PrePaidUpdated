package com.example.prepaidcardsdk.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.rememberCoroutineScope
import com.example.prepaidcard.utils.STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


//sealed class NetworkResponse<T>(val data: T? = null, val message: String? = null) {
//
//    class Success<T>(data: T? = null) : NetworkResponse<T>(data)
//
//    class Error<T>(message: String, data: T? = null) : NetworkResponse<T>(data, message)
//
//    class Loading<T>(val isLoading: Boolean) : NetworkResponse<T>(null)
//
//}


suspend fun <T> Call<T>.awaitVex(): T = suspendCoroutine { continuation ->
    val callback = object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            continuation.resumeNormallyOrWithException {
                response.isSuccessful || throw IllegalStateException("Http error ${response.code()}")
                response.body() ?: throw IllegalStateException("Response body is null")
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) = continuation.resumeWithException(t)
    }
    enqueue(callback)
}

private inline fun <T> Continuation<T>.resumeNormallyOrWithException(getter: () -> T) = try {
    val result = getter()
    resume(result)
} catch (exception: Throwable) {
    resumeWithException(exception)
}


/**
 * returnResponseBodyFlow handle the API response,
 * convert the dto response to domain response
 * extracting the error according to the error code
 * **/
@SuppressLint("LogNotTimber")


fun <T, O> handleFlowResponse(
    call: suspend () -> Response<T>, mapFun: (it: T) -> O
): Flow<NetworkResponse<O>> {

    return flow {
        emit(NetworkResponse.Loading(true))
        delay(1000)
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val data = response.body()?.let { mapFun(it) }
                if(data!=null){
                    emit(NetworkResponse.Success(data))
                }

            } else {
                var errorMsg = ""
                val errorBody = response.errorBody()!!.string()
                when {
                    response.code() == 400 -> {
                        try {
                            val jObjError = JSONObject(errorBody)
                            if (jObjError.has("apiComment")) {
                                errorMsg = jObjError.getString("apiComment")
                            }
                            if (jObjError.has("data")) {
                                try {
                                    val dataObj = jObjError.getJSONObject("data")
                                    if (dataObj.has("statusDesc")) {
                                        errorMsg = dataObj.getString("statusDesc")
                                    }
                                } catch (e: Exception) {
                                    if (jObjError.has("data")) {
                                        errorMsg = jObjError.getString("data")
                                    }
                                }
                            }
                            if (jObjError.has("transactionStatus")) {
                                errorMsg = jObjError.getString("transactionStatus")
                            }
                            if (jObjError.has("message")) {
                                errorMsg = jObjError.getString("message")
                            }
                            emit(NetworkResponse.Error(errorMsg))
                        } catch (e: Exception) {
                            emit(NetworkResponse.Error("UNKNOWN ERROR"))
                        }
                    }
                    response.code() == 401 -> {
                        try {
                            val jObjError = JSONObject(errorBody)
                            if (jObjError.has("fault")) {
                                val faultError = jObjError.getJSONObject("fault")
                                if (faultError.has("faultstring")) {
                                    errorMsg = faultError.getString("faultstring")
                                }
                            }
                            if (errorMsg.isNotEmpty()) {
                                emit(NetworkResponse.Error(errorMsg))
                            } else {
                                emit(NetworkResponse.Error(STRING.somethingWrongMsg))
                            }
                        }catch (e:Exception){
                            emit(NetworkResponse.Error("Invalid Client Id or Client Secret"))
                        }
                    }
                    response.code() == 422 -> {
                        val jObjError = JSONObject(errorBody)
                        if (jObjError.has("message")) {
                            errorMsg = jObjError.getString("message")
                        }
                        if (errorMsg.isNotEmpty()) {
                            emit(NetworkResponse.Error(errorMsg))
                        } else {
                            emit(NetworkResponse.Error("Something Went Wrong"))
                        }
                    }
                    response.code() == 500 -> {
                        val jObjError = JSONObject(errorBody)
                        if (jObjError.has("fault")) {
                            val faultError = jObjError.getJSONObject("fault")
                            if (faultError.has("faultstring")) {
                                errorMsg = faultError.getString("faultstring")
                            }
                        }
                        if (jObjError.has("apiComment")) {
                            errorMsg = jObjError.getString("apiComment")
                        }
                        if (errorMsg.isNotEmpty()) {
                            emit(NetworkResponse.Error(errorMsg))
                        } else {
                            emit(NetworkResponse.Error("Something Went Wrong"))
                        }
                    }
                    else -> {
                        try {
                            val errorBody = response.errorBody()!!.string()
                            val jObjError = JSONObject(errorBody)
                            var errorMsg = ""
                            if (jObjError.has("message")) {
                                errorMsg = jObjError.getString("message")
                            }
                            if (jObjError.has("data")) {
                                errorMsg = jObjError.getString("data")
                            }

                            if (errorMsg.isNotEmpty()) {
                                emit(NetworkResponse.Error(errorMsg))
                            } else {
                                emit(NetworkResponse.Error(jObjError.toString()))
                            }
                        } catch (e: Exception) {
                            emit(NetworkResponse.Error("UNKNOWN ERROR"))
                        }
                    }
                }
            }
        } catch (e: IOException) {
            e.message?.let { emit(NetworkResponse.Error(it)) }
        } catch (e: HttpException) {
            e.message?.let { emit(NetworkResponse.Error(it)) }
        } catch (e: IllegalStateException) {
            e.message?.let { emit(NetworkResponse.Error(it)) }
        } catch (e: Exception) {
            e.message?.let { emit(NetworkResponse.Error(it)) }
        }
        emit(NetworkResponse.Loading(false))
    }
}


/**
 * handle() takes the response from use case function as Resource<> with in Main Coroutine Scope
 * return the extracted response with in onLoading(),onFailure(),onSuccess()
 * **/
fun <T> handleFlow(
    response: Flow<NetworkResponse<T>>,
    onLoading: (it: Boolean) -> Unit,
    onFailure: (it: String) -> Unit,
    onSuccess: (it: T?) -> Unit
) {
    CoroutineScope(Dispatchers.Main).launch {
        response.collectLatest {
            when (it) {
                is NetworkResponse.Error -> {
                    onFailure.invoke(it.message ?: "")
                }

                is NetworkResponse.Loading -> {
                    onLoading.invoke(it.isLoading)
                }

                is NetworkResponse.Success -> {

                    onSuccess.invoke(it.data)

                }
            }
        }
    }
}
