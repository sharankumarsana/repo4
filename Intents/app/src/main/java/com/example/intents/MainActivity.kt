package com.example.intents

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tapadoo.alerter.Alerter

class MainActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var photo: Button
    private lateinit var email: Button
    private lateinit var share: Button
    private lateinit var opengc: Button
    private lateinit var aboutc: EditText
    private lateinit var name: EditText
    private lateinit var num: EditText

    private val emailsub: String = "Contact Info"
    private val url:String="https://contacts.google.com/"

    private val requestCapture = 1
    private val pickImage = 1000
    private val permissionCode = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.title)
        img = findViewById(R.id.snap)
        photo = findViewById(R.id.shutterButton)
        email = findViewById(R.id.email)
        aboutc = findViewById(R.id.about)
        share = findViewById(R.id.share)
        opengc = findViewById(R.id.opengc)
       num=findViewById(R.id.phonenum)

        photo.setOnClickListener {
            val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
            val alert=AlertDialog.Builder(this)
            alert.setTitle("Change Profile Photo")
            alert.setItems(options){dialog, which->
                when(which){
                    0->{
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePictureIntent, requestCapture)
                    }
                    1->{
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                                //permission denied
                                val permission= arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                requestPermissions(permission,permissionCode)
                            }
                            else{
                                //permission granted
                                pickimagefromgallery()
                            }
                        }
                    }
                    2->{
                        dialog.dismiss()}
                }
            }
            val dialog = alert.create()
            dialog.show()
        }
        email.setOnClickListener {
            val emailbody = aboutc.text.toString().trim()
            val num= num.text.toString()
            sendMail(emailsub, emailbody,num)
        }
        share.setOnClickListener {
            val about=aboutc.text.toString()
            val phone = num.text.toString()
            val shareIntent:Intent= Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_TEXT,about)
                putExtra(Intent.EXTRA_TEXT,phone)
            }
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            }
        }
        opengc.setOnClickListener {
            Log.d("Web","Button clicked")
            val webpage: Uri = Uri.parse(url)
            val webintent = Intent(Intent.ACTION_VIEW,webpage)
            startActivity(webintent)
            if (webintent.resolveActivity(packageManager) != null) {
                startActivity(webintent)
            }
        }
        name.setOnFocusChangeListener { view, b ->
            if(!b){
                Alerter.Companion.create(this)
                    .setTitle("Notification")
                    .setText("Name Entered")
                    .setIcon(R.drawable.done)
                    .setBackgroundColorRes(R.color.black)
                    .setDuration(4000)
                    .show()
            }
        }
        aboutc.setOnFocusChangeListener { view, b ->
            if(!b){
                Alerter.Companion.create(this)
                    .setTitle("Notification")
                    .setText("Entered the Details of the contact")
                    .setIcon(R.drawable.done)
                    .setBackgroundColorRes(R.color.black)
                    .setDuration(4000)
                    .show()
            }
        }
    }
    private fun sendMail(emailSub: String, emailBody: String,numberc:String) {
        val emailIntent: Intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")

            putExtra(Intent.EXTRA_SUBJECT, emailSub)
            putExtra(Intent.EXTRA_TEXT, emailBody)
            putExtra(Intent.EXTRA_TEXT,numberc)
        }
        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(emailIntent)
        }
    }

    private fun pickimagefromgallery() {
        val photointent:Intent=Intent(Intent.ACTION_PICK)
        photointent.type="image/*"
        startActivityForResult(photointent,pickImage)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            permissionCode->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickimagefromgallery()
                }
                else{
                    Toast.makeText(this,"Permission Required",Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==requestCapture && resultCode== RESULT_OK){
                val imageBitmap = data?.extras?.get("data") as? Bitmap
                img.setImageBitmap(imageBitmap)
            }
            else{
                if (data != null) {
                    img.setImageURI(data.data)
                }
            }
    }

}