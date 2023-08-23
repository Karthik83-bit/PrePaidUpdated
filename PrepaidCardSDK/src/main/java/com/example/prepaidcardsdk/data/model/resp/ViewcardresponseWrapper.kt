package com.example.prepaidcardsdk.data.model.resp

import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.charset.StandardCharsets
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

data class ViewcardresponseWrapper(
    val cardRefId: String,
    val cardType: String,
    val encryptedCard: String,
    val encryptedCvv: Any,
    val expiryDate: String,
    val isActive: Boolean,
    val isBlock: Boolean,
    val isHotlist: Boolean,
    val lastfourDigit: String,
    val nameonCard: String,
    val productName: String,
    val isVirtual:Boolean,
    val balance:String
)








@RequiresApi(Build.VERSION_CODES.O)
private fun decryptData(encryptedData: ByteArray, key: ByteArray): String {
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