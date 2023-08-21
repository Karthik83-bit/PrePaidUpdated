package com.example.prepaidcardsdk.data.model.resp

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
    val isVirtual:Boolean
)

//    "viewcardresponseWrapper": {
//    "cardRefId": "1187208",
//    "productName": "test",
//    "isActive": true,
//    "expiryDate": "2710",
//    "isBlock": false,
//    "isHotlist": false,
//    "lastfourDigit": "0",
//    "cardType": "GPR",
//    "encryptedCvv": "cfOZGYziZWoxrbXX3sV0fA==",
//    "encryptedCard": "9RtaLAQb4uoaKqYMAU6dO3j37D/sfn5DNxvB7BijBEE=",
//    "nameonCard": "Sushree",
//    "isVirtual": false
//}
