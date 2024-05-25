package com.example.loginregistersqlitedatabase

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginregistersqlitedatabase.databinding.ActivityLoginBinding
import com.example.loginregistersqlitedatabase.databinding.ActivityRegisterSuccessfullBinding
import com.google.android.material.snackbar.Snackbar

class RegisterSuccessfullActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityRegisterSuccessfullBinding
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccessfullBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraEmail = intent.getStringExtra("EMAIL_DATA")

        db = DatabaseHelper(this)

        val message = "Congratulations, your account has been successfully created with email: $extraEmail"
        val spannableString = SpannableString(message)
        val emailStart = message.indexOf(extraEmail!!)
        val emailEnd = emailStart + extraEmail.length
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF3F51B5")), emailStart, emailEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), emailStart, emailEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvRegisterMessage.text = spannableString

        binding.btnReturnLogin.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}