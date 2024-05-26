package com.example.loginregistersqlitedatabase

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginregistersqlitedatabase.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener{
            loginUser()
            binding.textInputPassword.text?.clear()
        }
    }

    private fun loginUser() {
        val email = binding.textInputEmail.text.toString().trim()
        val password = binding.textInputPassword.text.toString().trim()

        if(ValidationUtils.isTextNotEmpty(email) && ValidationUtils.isTextNotEmpty(password)){
            if(ValidationUtils.isValidEmail(email)){
                val isSuccess = db.checkUser(email, password)
                if(isSuccess){
                    Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,HomeActivity::class.java)
                    i.putExtra("EMAIL_DATA", email)
                    startActivity(i)
                    finish()
                }else{
                    Toast.makeText(this,"Login failed", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Invalid email format ...", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Please enter all field ...", Toast.LENGTH_SHORT).show()
        }
    }
}