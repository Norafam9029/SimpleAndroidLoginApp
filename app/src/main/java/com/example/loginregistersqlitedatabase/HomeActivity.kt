package com.example.loginregistersqlitedatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginregistersqlitedatabase.databinding.ActivityHomeBinding
import com.example.loginregistersqlitedatabase.databinding.ActivityRegisterBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extraEmail = intent.getStringExtra("EMAIL_DATA")


        db = DatabaseHelper(this)

        binding.btnLogout.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.tvPrimaryAccount.text = "Your account: $extraEmail"

    }
}