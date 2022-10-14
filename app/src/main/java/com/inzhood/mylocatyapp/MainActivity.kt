package com.inzhood.mylocatyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inzhood.mylocatyapp.databinding.ActivityMainBinding
import com.inzhood.mylocatyapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}