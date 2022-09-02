package `in`.kay.phash

import android.content.Context
import android.graphics.*
import android.net.Uri

class Phash(
    fileOnePath: String?,
    fileTwoPath: String?,
    bitSize: Int = 8
) {
    private var bitMapOne: Bitmap? = null
    private var bitMapTwo: Bitmap? = null
    private var hashOne: String? = null
    private var hashTwo: String? = null
    private var hDistance: Int = 101

    constructor(uriOne: Uri?, uriTwo: Uri?, context: Context) : this(
        FileUtils.getPath(context, uriOne)!!, FileUtils.getPath(context, uriTwo)!!
    )

    init {
        // First we are converting our image to 8x8 bits
        bitMapOne = resizeToNxN(fileOnePath, bitSize)
        bitMapTwo = resizeToNxN(fileTwoPath, bitSize)
        // Then converting bitmap to grayscale
        bitMapOne = bitMapOne?.let { toGreyscale(it) }
        bitMapTwo = bitMapTwo?.let { toGreyscale(it) }
        // Getting the hash from the images
        hashOne = bitMapOne?.let { buildHash(it) }
        hashTwo = bitMapTwo?.let { buildHash(it) }
        // Finally getting hamming distance
        hDistance = getHammingDistance(hashOne.toString(), hashTwo.toString())
    }

    fun getSimilarityScore(): Int {
        return 100 - hDistance
    }

    private fun getHammingDistance(one: String, two: String): Int {
        if (one.length != two.length) {
            return -1
        }
        var counter = 0
        for (i in one.indices) {
            if (one[i] != two[i]) counter++
        }
        return counter
    }

    private fun resizeToNxN(filePath: String?, N: Int): Bitmap? {
        val originalBitmap = BitmapFactory.decodeFile(filePath) ?: return null
        return Bitmap.createScaledBitmap(originalBitmap, N, N, true)
    }

    private fun toGreyscale(bmpOriginal: Bitmap): Bitmap? {
        val height: Int = bmpOriginal.height
        val width: Int = bmpOriginal.width
        val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmpGrayscale)
        val ma = ColorMatrix()
        ma.setSaturation(0f)
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(ma)
        canvas.drawBitmap(bmpOriginal, 0f, 0f, paint)
        return bmpGrayscale
    }

    private fun buildHash(grayscaleBitmap: Bitmap): String {
        val myHeight = grayscaleBitmap.height
        val myWidth = grayscaleBitmap.width
        var totalPixVal = 0
        for (i in 0 until myWidth) {
            for (j in 0 until myHeight) {
                val currPixel = grayscaleBitmap.getPixel(i, j) and 0xff //read lowest byte of pixels
                totalPixVal += currPixel
            }
        }
        val average = totalPixVal / 64
        var hashVal = ""
        for (i in 0 until myWidth) {
            for (j in 0 until myHeight) {
                val currPixel = grayscaleBitmap.getPixel(i, j) and 0xff //read lowest byte of pixels
                hashVal += if (currPixel >= average) {
                    "1"
                } else {
                    "0"
                }
            }
        }
        return hashVal
    }

}