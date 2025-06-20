package com.example.projse.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projse.databinding.ActivitySplash2Binding

class SplashActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySplash2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startBtn.setOnClickListener {
                startActivity(Intent(this@SplashActivity2, MainActivity::class.java))
                finish()
            }
        }

    }
}