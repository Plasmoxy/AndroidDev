package io.github.plasmoxy.begin.widgetmania

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    private val CAMERA_REQUEST = 1888

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        captureButton.setOnClickListener { 
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = when(requestCode) {
        
        CAMERA_REQUEST -> {
            val photo = data?.extras?.get("data") as Bitmap?
            if (photo != null) {
                imageView.setImageBitmap(photo)
            } else {}
        }
        
        else -> {}
    }
}
