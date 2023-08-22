package com.example.prepaidcardsdk.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object EncryptDecrypt {
    val key=   "ASDFGHJASHJKLQWEASDFGHJASHJKLQWE".toByteArray(StandardCharsets.UTF_8)
    @RequiresApi(Build.VERSION_CODES.O)
     fun decryptData(encryptedData: ByteArray, key: ByteArray): String {
        var cipher: Cipher? = null
        var decryptedData: ByteArray? = null
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            val secretKeySpec = SecretKeySpec(key, "AES")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
            decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return decryptedData?.let { String(it, StandardCharsets.UTF_8) } ?: ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encryptData(data: String, key: ByteArray): String {
        println("Before Encrypt")
        println(data)
        val dataToSend = data.toByteArray(StandardCharsets.UTF_8)
        println(dataToSend)
        println(String(dataToSend))
        var cipher: Cipher? = null
        var encryptedData: ByteArray? = null
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
            val secretKeySpec = SecretKeySpec(key, "AES")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
            encryptedData = cipher.doFinal(dataToSend)
            println(encryptedData)
        } catch (e: Exception) {

            e.printStackTrace()
        }
        val encryptedByteValue = Base64.getEncoder().encode(encryptedData)
        return String(encryptedByteValue)
    }
}