package com.github.flinkou.kinopoisktop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.flinkou.kinopoisktop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}