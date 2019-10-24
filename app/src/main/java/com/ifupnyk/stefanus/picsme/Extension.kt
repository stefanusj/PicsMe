package com.ifupnyk.stefanus.picsme

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun String.toUri(): Uri =
        Uri.parse(this)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun Context.saveBitmap(bmp: Bitmap): File? {

    val pathName =
        Environment.getExternalStorageDirectory().absolutePath + "/${this.resources.getString(R.string.app_name)}"
    val myDir = File(pathName)
    myDir.mkdirs()

    val dateFormat = SimpleDateFormat("yyyyMMdd_HH_mm_ss", Locale.US)
    val currentTimeStamp = dateFormat.format(Date())
    val fileName = "IMG_$currentTimeStamp.jpg"

    val file = File(myDir, fileName)
    if (file.exists()) file.delete()
    try {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val f = File(pathName, fileName)
        f.createNewFile()
        val fo = FileOutputStream(f)
        fo.write(baos.toByteArray())
        fo.close()
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
    return file
}
