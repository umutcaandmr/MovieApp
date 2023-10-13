package com.umutdemir.movieapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umutdemir.movieapp.R
import com.umutdemir.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}