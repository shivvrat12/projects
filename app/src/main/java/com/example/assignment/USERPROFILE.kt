package com.example.assignment

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUserMetadata
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser as FirebaseUser

class USERPROFILE : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)
        var firstname = findViewById<EditText>(R.id.firstname)
        var lastname = findViewById<EditText>(R.id.lastname)
        var email = findViewById<EditText>(R.id.email)
        var done = findViewById<Button>(R.id.saveBTN)
        var db = Firebase.firestore
        var User = Firebase.auth.currentUser
        var refresh = findViewById<Button>(R.id.refreshBTN)
        var fname = findViewById<TextView>(R.id.firstnameview)
        var lname = findViewById<TextView>(R.id.lastnameview)
        var mail = findViewById<TextView>(R.id.emailview)

        done.setOnClickListener {
            var Firstname: String = firstname.text.toString()
            var Lastname: String = lastname.text.toString()
            var Email: String = email.text.toString()
            val user: MutableMap<String, Any> = mutableMapOf()
            user.put("First Name",Firstname)
            user.put("Last Name", Lastname)
            user.put("Email", Email)

            if (User != null) {
                db.collection("user").document(User.uid)
                    .set(user)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                    }
            }
        }
        refresh.setOnClickListener {
            db.collection("user")
                .get()
                .addOnSuccessListener { result ->
                    for(document in result){
                        Log.d(TAG, "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
                }
        }
    }