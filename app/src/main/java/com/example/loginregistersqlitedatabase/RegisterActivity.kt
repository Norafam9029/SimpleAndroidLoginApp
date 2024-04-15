package com.example.loginregistersqlitedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginregistersqlitedatabase.databinding.ActivityLoginBinding
import com.example.loginregistersqlitedatabase.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityRegisterBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        binding.tvLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser() {
        val username = binding.textInputUsername.text.toString()
        val email = binding.textInputEmail.text.toString()
        val password = binding.textInputPassword.text.toString()

        if(ValidationUtils.isTextNotEmpty(username) &&
            ValidationUtils.isTextNotEmpty(email) &&
            ValidationUtils.isTextNotEmpty(password)
        ){
            if(ValidationUtils.isValidEmail(email)){
                val user = User(username = username, email = email.trim(), password = password)
                db.registerUser(user)
                Toast.makeText(this,"User registered...!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Invalid email format", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Please input all field", Toast.LENGTH_SHORT).show()
        }
    }
}