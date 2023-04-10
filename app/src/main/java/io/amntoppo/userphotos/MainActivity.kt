package io.amntoppo.userphotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.amntoppo.userphotos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}