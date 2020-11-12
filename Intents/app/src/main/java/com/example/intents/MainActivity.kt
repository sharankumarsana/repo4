package com.example.intents

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var photo: Button
    private lateinit var email: Button
    private lateinit var share: Button
    private lateinit var opengc: Button
    //private lateinit var entered:Button
    private lateinit var aboutc: EditText
    private lateinit var name: EditText
    private lateinit var num: EditText
val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID1 = 1
    val NOTIFICATION_ID2 = 2
    val NOTIFICATION_ID3=3
    private val emailsub: String = "Contact Info"
    private val url:String="https://contacts.google.com/"

    private val requestCapture = 1
    private val pickImage = 1000
    private val permissionCode = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        name=findViewById(R.id.title)
        img = findViewById(R.id.snap)
        photo = findViewById(R.id.shutterButton)
        email = findViewById(R.id.email)
        aboutc = findViewById(R.id.about)
        share = findViewById(R.id.share)
        opengc = findViewById(R.id.opengc)
       num=findViewById(R.id.phonenum)
//entered=findViewById(R.id.enteredall)
        val notification1 =  NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText("entered name")
            .setSmallIcon(R.drawable.done)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notification2 =  NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText("Entered about him")
            .setSmallIcon(R.drawable.done)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notification3 =  NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText("entered phonenum")
            .setSmallIcon(R.drawable.done)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
//entered.setOnClickListener{

    //notificationManager.notify(NOTIFICATION_ID,notification)
//}

        photo.setOnClickListener {
            val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
            val alert=AlertDialog.Builder(this)
            alert.setTitle("Change Profile Photo")
            //android.content public interface DialogInterface
            //Interface that defines a dialog-type class that can be shown, dismissed, or canceled, and may have buttons that can be clicked.
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
        name.setOnFocusChangeListener { view, b ->
            if(!b){
                notificationManager.notify(NOTIFICATION_ID1,notification1)
            }
        }
        aboutc.setOnFocusChangeListener { view, b ->
            if(!b){
                notificationManager.notify(NOTIFICATION_ID2,notification2)
            }
        }
        num.setOnFocusChangeListener { view, b ->
            if(!b){
                notificationManager.notify(NOTIFICATION_ID3,notification3)
            }
        }

        email.setOnClickListener {
            val emailbody = aboutc.text.toString().trim()
            val num= num.text.toString()
            sendMail(emailsub, emailbody,num)
        }
        share.setOnClickListener {
            //val about=aboutc.text.toString()
            val phone = num.text.toString()
            val shareIntent:Intent= Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                //putExtra(Intent.EXTRA_TEXT,about)
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




    }
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
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
                //extras=This is a Bundle of any additional information. This can be used to provide extended information to the component. For example, if we have a action to send an e-mail message, we could also include extra pieces of data here to supply a subject, body, etc.
                img.setImageBitmap(imageBitmap)
            }
            else{
                if (data != null) {
                    img.setImageURI(data.data)
                }
            }
    }

}