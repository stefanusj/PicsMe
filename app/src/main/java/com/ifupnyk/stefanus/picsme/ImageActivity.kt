package com.ifupnyk.stefanus.picsme

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_image.*
import org.jetbrains.anko.toast
import org.opencv.android.OpenCVLoader


class ImageActivity : AppCompatActivity() {

    private lateinit var original: Bitmap
    private lateinit var edited: Bitmap

    init {
        OpenCVLoader.initDebug()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageUri = intent.getStringExtra(ImageActivity.IMAGE_INTENT).toUri()
        original = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
        ivPhoto.setImageBitmap(original)

        flOriginal.setOnClickListener(onOriginalClicked)
        flGrayscale.setOnClickListener(onGrayscaleClicked)
        flSepia.setOnClickListener(onSepiaClicked)
        flBrightness.setOnClickListener(onBrightnessClicked)
        flBlur.setOnClickListener(onBlurClicked)
        flAutumn.setOnClickListener(onAutumnClicked)
        flBone.setOnClickListener(onBoneClicked)
        flJet.setOnClickListener(onJetClicked)
        flWinter.setOnClickListener(onWinterClicked)
        flRainbow.setOnClickListener(onRainbowClicked)
        flOcean.setOnClickListener(onOceanClicked)
        flSummer.setOnClickListener(onSummerClicked)
        flSpring.setOnClickListener(onSpringClicked)
        flCool.setOnClickListener(onCoolClicked)
        flHsv.setOnClickListener(onHsvClicked)
        flPink.setOnClickListener(onPinkClicked)
        flHot.setOnClickListener(onHotClicked)
        flParula.setOnClickListener(onParulaClicked)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        STORAGE_PERMISSION)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        flOriginal.performClick()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.image_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menuSave -> saveImage()
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            STORAGE_PERMISSION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults.get(0) == PackageManager.PERMISSION_GRANTED) {
                } else {
                    toast("Permission harus diijinkan untuk melanjutkan")
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    val onOriginalClicked = View.OnClickListener {
        frameActivate(flOriginal)
        edited = Bitmap.createBitmap(original)
        ivPhoto.setImageBitmap(edited)
    }

    val onGrayscaleClicked = View.OnClickListener {
        frameActivate(flGrayscale)
        edited = original.toMat().toGrayscale().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onSepiaClicked = View.OnClickListener {
        frameActivate(flSepia)
        edited = original.toMat().toSepia().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onBrightnessClicked = View.OnClickListener {
        seekBar.setOnSeekBarChangeListener(onSeekBarBrightnessChanged)
        frameActivate(flBrightness)
        edited = original.toMat().brightness().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onBlurClicked = View.OnClickListener {
        seekBar.setOnSeekBarChangeListener(onSeekBarBlurChanged)
        frameActivate(flBlur)
        edited = original.toMat().toBlur().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onAutumnClicked = View.OnClickListener {
        frameActivate(flAutumn)
        edited = original.toMat().toGrayscale().toColor().toAutumn().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onBoneClicked = View.OnClickListener {
        frameActivate(flBone)
        edited = original.toMat().toGrayscale().toColor().toBone().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }
    val onJetClicked = View.OnClickListener {
        frameActivate(flJet)
        edited = original.toMat().toGrayscale().toColor().toJet().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onWinterClicked = View.OnClickListener {
        frameActivate(flWinter)
        edited = original.toMat().toGrayscale().toColor().toWinter().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onRainbowClicked = View.OnClickListener {
        frameActivate(flRainbow)
        edited = original.toMat().toGrayscale().toColor().toRainbow().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onOceanClicked = View.OnClickListener {
        frameActivate(flOcean)
        edited = original.toMat().toGrayscale().toColor().toOcean().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onSummerClicked = View.OnClickListener {
        frameActivate(flSummer)
        edited = original.toMat().toGrayscale().toColor().toSummer().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onSpringClicked = View.OnClickListener {
        frameActivate(flSpring)
        edited = original.toMat().toGrayscale().toColor().toSpring().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onCoolClicked = View.OnClickListener {
        frameActivate(flCool)
        edited = original.toMat().toGrayscale().toColor().toCool().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onHsvClicked = View.OnClickListener {
        frameActivate(flHsv)
        edited = original.toMat().toGrayscale().toColor().toHsv().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onPinkClicked = View.OnClickListener {
        frameActivate(flPink)
        edited = original.toMat().toGrayscale().toColor().toPink().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onHotClicked = View.OnClickListener {
        frameActivate(flHot)
        edited = original.toMat().toGrayscale().toColor().toHot().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onParulaClicked = View.OnClickListener {
        frameActivate(flParula)
        edited = original.toMat().toGrayscale().toColor().toParula().toBitmap()
        ivPhoto.setImageBitmap(edited)
    }

    val onSeekBarBlurChanged = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            tvValue.text = progress.inc().toString()

            frameActivate(flBlur)
            edited = original.toMat().toBlur(progress.inc(), progress.inc()).toBitmap()
            ivPhoto.setImageBitmap(edited)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    val onSeekBarBrightnessChanged = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            tvValue.text = progress.inc().toString()

            frameActivate(flBrightness)
            edited = original.toMat().brightness(progress.inc()).toBitmap()
            ivPhoto.setImageBitmap(edited)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    }

    fun frameActivate(frameSelected: FrameLayout) {
        val frames = arrayListOf<FrameLayout>(
                flOriginal, flGrayscale, flSepia, flBrightness, flBlur,
                flAutumn, flBone, flJet, flWinter, flRainbow,
                flOcean, flSummer, flSpring, flCool,
                flHsv, flPink, flHot, flParula
        )
        for (frame in frames) {
            frame.isSelected = frame.equals(frameSelected)
        }

        if (frameSelected.equals(flBlur) or frameSelected.equals(flBrightness))
            llSeekBar.visible()
        else
            llSeekBar.invisible()
    }

    fun saveImage() {
        if (savebitmap(edited))
            toast("Image Saved Successfully")
        else
            toast("Failed to Save")
    }

    companion object {
        const val IMAGE_INTENT = "image"
        const val STORAGE_PERMISSION = 111
    }
}
