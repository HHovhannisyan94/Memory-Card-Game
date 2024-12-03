package com.example.matchinggame.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.matchinggame.R
import com.example.matchinggame.databinding.ActivityMainBinding

import com.example.matchinggame.presentation.ui.fragment.GameMenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

           supportFragmentManager.beginTransaction()
                .add(R.id.fragment_section, GameMenuFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

}