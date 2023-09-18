package com.seymasingin.recyclerviewhomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seymasingin.recyclerviewhomework.common.viewBinding
import com.seymasingin.recyclerviewhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding:: inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}