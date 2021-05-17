package com.example.g_logements.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.g_logements.R
import com.example.g_logements.models.user
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registration : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    lateinit var myReference: DatabaseReference

    lateinit var nom:EditText
    lateinit var username:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var image:EditText
    lateinit var isAdminn:EditText
    lateinit var bt_reg:Button
    lateinit var bt_reset:Button
    var maxid: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        nom = findViewById(R.id.et_nom)
        username = findViewById(R.id.et_username)
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        image = findViewById(R.id.et_img)
        isAdminn = findViewById(R.id.et_isAdmin)
        bt_reg = findViewById(R.id.bt_reg)
        bt_reset = findViewById(R.id.bt_reset)

        database = FirebaseDatabase.getInstance()
        myReference = database.getReference("users")
        var id:String =myReference.push().key as String
        bt_reg.setOnClickListener {
            val u = user(nom.text.toString(),
            username.text.toString(),
            email.text.toString(),
            password.text.toString(),
            image.text.toString().toInt(),
                isAdminn.text.toString().toBoolean())
            myReference.child((id)).setValue(u).addOnSuccessListener {
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                var intent = Intent(applicationContext, Login::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_LONG).show()
            }
        }
    }
}