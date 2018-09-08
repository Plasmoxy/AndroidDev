package com.shardbytes.plasmoxy.juncc.writingfiles

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.obsez.android.lib.filechooser.ChooserDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.io.File

const val PERMISSIONRESULT_STORAGE = 1

class MainActivity : AppCompatActivity() {

    val isExternalStorageWritable: Boolean get() =
        Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED
    
    private lateinit var textfile: File
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // setup file
        textfile = File(Environment.getExternalStorageDirectory(), "boi.txt")
        
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
        
        buttonChoose.setOnClickListener {
            ChooserDialog().with(this)
                    .withFilter(true, true)
                    .withStartFile(Environment.getExternalStorageDirectory().absolutePath)
                    .withChosenListener { path, dir -> 
                        textfile = File(dir, "boi.txt")
                        toast("directory set!")
                    }
                    .build()
                    .show()
        }
        
        buttonWrite.setOnClickListener { 
            textfile.apply {
                if (!exists()) createNewFile()
                writeText(Math.random().toString())
            }
        }
        
        buttonRead.setOnClickListener {
            toast(
                    textfile.readText()
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
