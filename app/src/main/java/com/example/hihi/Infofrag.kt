package com.example.hihi

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hihi.databinding.FragmentInfofragBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Infofrag : Fragment() {

    private lateinit var binding: FragmentInfofragBinding
    private lateinit var serv: String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var namo: String
    private lateinit var ageo: String
    private lateinit var cityo: String
    private lateinit var servico: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference()
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.uid.toString()

        binding = FragmentInfofragBinding.inflate(inflater, container, false)



        binding.customer.setOnClickListener {
            binding.customer.setBackgroundColor(Color.parseColor("#AA96DA"))
            binding.vendor.setBackgroundColor(Color.parseColor("#DBD9E1"))

            serv = "customer"
        }
        binding.vendor.setOnClickListener {
            binding.vendor.setBackgroundColor(Color.parseColor("#AA96DA"))
            binding.customer.setBackgroundColor(Color.parseColor("#DBD9E1"))

            serv = "vendor"
        }
        binding.save.setOnClickListener {
            val user = firebaseAuth.uid.toString()
            databaseReference.child("users").child(user).child("Name").setValue(binding.name.text.toString())
            databaseReference.child("users").child(user).child("Age").setValue(binding.age.text.toString())
            databaseReference.child("users").child(user).child("City").setValue(binding.city.text.toString())
            databaseReference.child("users").child(user).child("service").setValue(serv)


        }
        return binding.root
    }



    }
