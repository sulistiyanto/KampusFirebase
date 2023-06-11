package com.kampus.merdeka.kampusfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.kampus.merdeka.kampusfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            throw RuntimeException("Test Crash")
        }
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
//                Log.d("KESINI", "isSuccessful")
                val welcomeMessagey = remoteConfig.getString("loading_phrase")
                Log.d("KESINI", welcomeMessagey.toString())
            } else {
                Log.d("KESINI", "isSuccessful ailed")
            }
        }
    }
}