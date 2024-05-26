package com.example.loginregistersqlitedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginregistersqlitedatabase.databinding.ActivityUpdatePasswordBinding

class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePasswordBinding
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val oldEmail = intent.getStringExtra("EMAIL_DATA")
        val oldUsername = intent.getStringExtra("USERNAME_DATA")

        findViewById<TextView>(R.id.text_update_email).text = oldEmail
        findViewById<TextView>(R.id.text_update_username).text = oldUsername

        db = DatabaseHelper(this)

        binding.btnUpdateAccount.setOnClickListener{
            updateUserInfo()
            binding.textUpdatePassword.text?.clear()
        }

        binding.btnReturn.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }

    private fun updateUserInfo() {
        val username = binding.textUpdateUsername.text.toString()
        val email = binding.textUpdateEmail.text.toString()
        val password = binding.textUpdatePassword.text.toString()
        val id = db.getUserID(email ?: "")?.toInt() ?: 0

        if(ValidationUtils.isTextNotEmpty(username) &&
            ValidationUtils.isTextNotEmpty(email) &&
            ValidationUtils.isTextNotEmpty(password)
        ){
            if(ValidationUtils.isValidEmail(email)){
                val user = User(id = id, username = username, email = email, password = password)
                val isSuccess = db.updateUser(user)
                if (isSuccess) {
                    Toast.makeText(this, "Update success!", Toast.LENGTH_LONG).show()
                    val i = Intent(this, HomeActivity::class.java)
                    i.putExtra("EMAIL_DATA", email)
                    startActivity(i)
                } else {
                    Toast.makeText(this, "Update failed!", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Invalid email format", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Please input all field", Toast.LENGTH_SHORT).show()
        }
    }
}