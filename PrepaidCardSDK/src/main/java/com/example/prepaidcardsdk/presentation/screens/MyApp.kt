package com.example.prepaidcardsdk.presentation.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.example.prepaidcardsdk.components.PdfViewer
import com.example.prepaidcardsdk.components.pdfFileUtils

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun MyApp(
 ) {

   val configuration = LocalConfiguration.current
   val screenWidthDp = configuration.screenWidthDp.toFloat()
    val pageHeight = 1120
    val pageWidth = 792
    val pdfDocument = PdfDocument()
    val faildStatus = Paint()
    val keys = Paint()
    val value = Paint()
    val amount = Paint()
    val centerData = Paint()
    val centerDataSmall = Paint()
    val rightData = Paint()
    val successStatus = Paint()
    val txnDetails = Paint()
    var num = 0
    var save: String
    val context= LocalContext.current

//    val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
//    val myPage = pdfDocument.startPage(pageInfo)
    @Composable
    fun drawinPage(ind:Int,data:List<String>,pageInd:Int) {
        var height=100f
        var startind=ind
        var pageIndex=pageInd
        val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageInd).create()
        val myPage = pdfDocument.startPage(pageInfo)
    val canvas=myPage.canvas
    canvas.drawText("Transaction History", pageWidth/2f, 100f, centerData)
    canvas.drawLine(10f,110f,2800f,110f,keys)
    canvas.drawText("Payment Date",20f,145f,keys)
    canvas.drawText("Status",240f,145f,keys)
    canvas.drawText("Beneficiary Name",380f,145f,keys)
    canvas.drawText("Ammount",640f,145f,keys)

        while(startind<data.size-1&&height<pageHeight){


            height+=100f
            canvas.drawText("1/09/23",100f,height,centerDataSmall)
            canvas.drawText("Successful",280f,height,centerDataSmall)
            canvas.drawText("P KARTHIK KUMAR",500f,height,centerDataSmall)
            canvas.drawText("2000",700f,height,centerDataSmall)
            startind+=1



        }
        pdfDocument.finishPage(myPage)
        if(ind==data.size-1){
            var folder = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString() + "/Unifier_Aeps_Report.pdf"
            )
            while (folder.exists()) {
                save = "Transaction Report" + num++ + ".pdf"
                folder = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), save
                )
            }
            try {
                pdfDocument.writeTo(FileOutputStream(folder))
                Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            pdfDocument.close()

            val pdfOpenIntent = Intent(Intent.ACTION_VIEW)
            pdfOpenIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pdfOpenIntent.setDataAndType(Uri.parse(folder.absolutePath), "application/pdf")
            try {
                context.startActivity(pdfOpenIntent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }

        }
        else{

            drawinPage(startind,data,pageIndex+1)
        }



    }

//    val context = LocalContext.current

//it is used for design pdf
//    val canvas: Canvas = myPage.canvas
/* below line is used for adding typeface for
our text which we will be adding in our PDF file.*/
    faildStatus.typeface = Typeface.create(
        Typeface.DEFAULT, Typeface.BOLD
    )
    keys.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    value.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    amount.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    successStatus.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    txnDetails.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
/* below line is used for setting text size
which we will be displaying in our PDF file.*/
    faildStatus.textSize = 40f
    faildStatus.color = Color.RED
    faildStatus.textAlign = Paint.Align.CENTER
    keys.textSize = 26f
    keys.textAlign = Paint.Align.LEFT
    successStatus.textSize = 40f
    successStatus.color = Color.GREEN
    successStatus.textAlign = Paint.Align.CENTER
    centerData.textSize = 36f
    centerData.textAlign = Paint.Align.CENTER
    centerDataSmall.textSize = 30f
    centerDataSmall.textAlign = Paint.Align.CENTER
    rightData.textSize = 26f
    rightData.textAlign = Paint.Align.RIGHT
    drawinPage(0, listOf("hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj","hjdkjhdkj",)
    ,1,
    )





//    canvas.drawText(data.CSC, 50f, 230f, keys)
//    canvas.drawText(data.AXIS, 50f, 280f, keys)
//    canvas.drawText(data.DATE, 50f, 330f, keys)
//    canvas.drawText(data.AGENT, 50f, 380f, keys)
//    canvas.drawText(data.LOCATION, 50f, 430f, keys)
//    canvas.drawText(data.Terminal, 50f, 480f, keys)
//    canvas.drawText(data.Stan, 50f, 530f, keys)
//    canvas.drawText(data.Txn, 50f, 580f, keys)
//    canvas.drawText(data.Rrn, 50f, 630f, keys)
//    canvas.drawText(data.Customer, 50f, 680f, keys)
//    canvas.drawText(data.Uid, 50f, 730f, keys)
//    canvas.drawText(data.Auth, 50f, 780f, keys)
//    canvas.drawText(data.Deposit, 50f, 830f, keys)
//    canvas.drawText(data.Msg, 50f, 880f, keys)
//    canvas.drawText(data.Bank, 50f, 930f, keys)
//    pdfDocument.finishPage(myPage)
//
//
//
//   var folder = File(
//      Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//         .toString() + "/Unifier_Aeps_Report.pdf"
//   )
//   while (folder.exists()) {
//      save = "Transaction Report" + num++ + ".pdf"
//      folder = File(
//         Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), save
//      )
//   }
//   try {
//      pdfDocument.writeTo(FileOutputStream(folder))
//      Toast.makeText(context, "PDF file generated successfully.", Toast.LENGTH_SHORT).show()
//   } catch (e: IOException) {
//      e.printStackTrace()
//   }
//   pdfDocument.close()
//   val path: Uri = FileProvider.getUriForFile(
//      context, context.applicationContext.packageName + ".provider", folder
//   )
//
//    pdfDocument.close()
//
////    val path: Uri = FileProvider.getUriForFile(
////        context, context.applicationContext.packageName + ".provider", folder
////    )


}


