package com.ifupnyk.stefanus.picsme

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc


fun Bitmap.toMat(): Mat {
    val mat = Mat()
    Utils.bitmapToMat(this, mat)
    return mat
}

fun Mat.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(this.cols(), this.rows(), Bitmap.Config.ARGB_8888)
    Utils.matToBitmap(this, bitmap)

    return bitmap
}

fun Mat.brightness(): Mat {
//    val src = Mat(bitmap.getHeight(), bitmap.getWidth(), CV_8UC1)
//    Utils.bitmapToMat(bitmap, src)
//    src.convertTo(src, -1, 1.0, value.toDouble())
//    val result = Bitmap.createBitmap(src.cols(), src.rows(), Bitmap.Config.ARGB_8888)
//    Utils.matToBitmap(src, result)
    this.convertTo(this, -1, 1.toDouble(), 0.toDouble())
    return this
}

fun Mat.brightness(value: Int = 0): Mat {
    this.convertTo(this, -1, 1.toDouble(), value.toDouble())
    return this
}

fun Mat.toGrayscale(): Mat {
    Imgproc.cvtColor(this, this, Imgproc.COLOR_BGR2GRAY)
    return this
}

fun Mat.toColor(): Mat {
    Imgproc.cvtColor(this, this, Imgproc.COLOR_GRAY2BGR)
    return this
}

fun Mat.toSepia(): Mat {
    val sepiaKernel = Mat(4, 4, CvType.CV_32F).apply {
        put(0, 0, /* R */floatArrayOf(0.189f, 0.769f, 0.393f, 0f))
        put(1, 0, /* G */floatArrayOf(0.168f, 0.686f, 0.349f, 0f))
        put(2, 0, /* B */floatArrayOf(0.131f, 0.534f, 0.272f, 0f))
        put(3, 0, /* A */floatArrayOf(0.000f, 0.000f, 0.000f, 1f))
    }
    Core.transform(this, this, sepiaKernel)
    return this
}

fun Mat.toBlur(width: Int = 50, height: Int = 50): Mat {
    Imgproc.blur(this, this, Size(width.toDouble(), height.toDouble()))
    return this
}

fun Mat.toAutumn(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_AUTUMN)
    return this
}

fun Mat.toBone(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_BONE)
    return this
}

fun Mat.toJet(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_JET)
    return this
}

fun Mat.toWinter(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_WINTER)
    return this
}

fun Mat.toRainbow(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_RAINBOW)
    return this
}

fun Mat.toOcean(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_OCEAN)
    return this
}

fun Mat.toSummer(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_SUMMER)
    return this
}

fun Mat.toSpring(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_SPRING)
    return this
}

fun Mat.toCool(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_COOL)
    return this
}

fun Mat.toHsv(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_HSV)
    return this
}

fun Mat.toPink(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_PINK)
    return this
}

fun Mat.toHot(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_HOT)
    return this
}

fun Mat.toParula(): Mat {
    Imgproc.applyColorMap(this, this, Imgproc.COLORMAP_PARULA)
    return this
}