package com.example.starwarsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.starwarsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val appComponents by lazy { AppAplication.appComponent }


    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponents.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val navController: NavController=Navigation.findNavController(this, R.id.fragment_view_main_container)
        navController.navigate(R.id.mainFragment_navigation)
    }
}