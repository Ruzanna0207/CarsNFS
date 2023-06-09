package com.example.listes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listes.databinding.ActivityMainBinding
import com.example.listes.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction().replace(R.id.frame, MainFragment.newInstance3()).commit()
    }
}
