package com.example.hihi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hihi.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class Signin : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gosignup.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signin.setOnClickListener {
            val ttemail = binding.mail.text.toString()
            val ttpass = binding.passwo.text.toString()
            if(ttemail.isNotEmpty() && ttpass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(ttemail, ttpass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, appp::class.java)
                        intent.putExtra("mail", ttemail)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}