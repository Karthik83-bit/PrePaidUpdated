package com.example.prepaidcardsdk.components

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

@Composable
fun PdfViewer(filePath: String) {

    val file = File(filePath)
    val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    val pdfRenderer = PdfRenderer(fileDescriptor)

    val bitmap = Bitmap.createBitmap(800, 1200, Bitmap.Config.ARGB_8888)

    val rendererPage = pdfRenderer.openPage(0)
    rendererPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
    rendererPage.close()

    pdfRenderer.close()
    fileDescriptor.close()

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}
