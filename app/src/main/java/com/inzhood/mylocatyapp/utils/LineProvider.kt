package com.inzhood.mylocatyapp.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object LineProvider {
    fun createLine(angle: Double, tilt: Double): Bitmap {
        val bitmap = Bitmap.createBitmap(5, tilt.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.RED)
        val paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8F
        paint.isAntiAlias = true
        val offset =50
        val stopX = angle.toInt()
        canvas.drawLine( // startX, startY, stopX, stopY start is the top, ie center point
            (canvas.width /2 ).toFloat(), //offset.toFloat(),
            (canvas.height / 2).toFloat(),
            (canvas.width - offset).toFloat(),
            (canvas.height /2).toFloat()
            , paint)
        return bitmap
    }
}

/*  original from https://www.tutorialspoint.com/how-to-draw-a-line-in-android-using-kotlin

 button.setOnClickListener {
         val bitmap = Bitmap.createBitmap(10, 700, Bitmap.Config.ARGB_8888)
         val canvas = Canvas(bitmap)
         canvas.drawColor(Color.RED)
         val paint = Paint()
         paint.color = Color.RED
         paint.style = Paint.Style.STROKE
         paint.strokeWidth = 8F
         paint.isAntiAlias = true
         val offset = 50
         canvas.drawLine(
         offset.toFloat(), (canvas.height / 2).toFloat(), (canvas.width - offset).toFloat(),          (canvas.height /
         2).toFloat(), paint)
         imageView.setImageBitmap(bitmap)
      }
 */