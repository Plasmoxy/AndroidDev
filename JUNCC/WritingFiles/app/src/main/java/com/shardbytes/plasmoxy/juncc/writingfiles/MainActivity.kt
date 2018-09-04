package com.shardbytes.plasmoxy.juncc.writingfiles

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.io.File

const val PERMISSIONRESULT_STORAGE = 1

class MainActivity : AppCompatActivity() {

    val isExternalStorageWritable: Boolean get() =
        Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED
    
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        buttonWritable.setOnClickListener { toast(if(isExternalStorageWritable) "yes" else "no") }
        
        buttonPermCheck.setOnClickListener {
            toast(
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED)
                        "granted"
                    else
                        "not granted"
            )
        }

        buttonPermRequest.setOnClickListener { 
            requestMandatoryPermissions()
        }
        
        buttonWrite.setOnClickListener { 
            File(Environment.getExternalStorageDirectory(), "boi.txt").apply {
                if (!exists()) createNewFile()
                writeText(Math.random().toString())
            }
        }
        
        buttonRead.setOnClickListener {
            toast(
                    File(Environment.getExternalStorageDirectory(), "boi.txt").readText()
            )
        }
        
    }

    override fun onResume() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestMandatoryPermissions()
        }
        super.onResume()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            PERMISSIONRESULT_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    toast("STORAGE GRANTED!")
                } else {
                    toast("STORAGE NOT GRANTED!")
                }
            }
        }
    }

    private fun requestMandatoryPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSIONRESULT_STORAGE
        )
    }
    
    
}
