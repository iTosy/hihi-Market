package com.example.hihi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hihi.databinding.ActivityApppBinding

class appp : AppCompatActivity() {
    private lateinit var binding: ActivityApppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var emo: String? = intent.getStringExtra("mail")
        binding = ActivityApppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.info.setOnClickListener {
            val myfrag = Infofrag()
            val bundle = Bundle()
            bundle.putString("mail", emo)
            myfrag.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.navo, Infofrag()).commit()
        }
        binding.map.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.navo, mapfrag()).commit()

        }
    }
}

