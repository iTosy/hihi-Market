package com.example.hihi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hihi.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var emailo: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference()

        binding.gosignin.setOnClickListener {
            Log.d("hehe", "sdds")
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
            Log.d("hehe2", "sdds")

        }
        binding.signup.setOnClickListener {
            val temail = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val con = binding.confirm.text.toString()
            emailo = temail

            if(temail.isNotEmpty() && pass.isNotEmpty() && con.isNotEmpty()){
                if(pass == con){
                    firebaseAuth.createUserWithEmailAndPassword(temail, pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            //databaseReference.child("users").setValue("users")
                            val user = firebaseAuth.uid.toString()
                            databaseReference.child("users").child(user).setValue("user")
                            databaseReference.child("users").child(user).child("Name").setValue(" ")
                            databaseReference.child("users").child(user).child("Age").setValue(" ")
                            databaseReference.child("users").child(user).child("City").setValue(" ")
                            databaseReference.child("users").child(user).child("service").setValue(" ")


                            val intent = Intent(this, Signin::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}