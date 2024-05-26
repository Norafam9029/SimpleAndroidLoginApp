package com.example.loginregistersqlitedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginregistersqlitedatabase.databinding.ActivityHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extraEmail = intent.getStringExtra("EMAIL_DATA")

        db = DatabaseHelper(this)

        val userName = db.getUserName(extraEmail ?: "")
        binding.tvPrimaryAccount.text = "Hello $userName"

        binding.btnLogout.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.btnSettings.setOnClickListener{
            // Create the BottomSheetDialog
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.activity_bottom_sheet_menu)

            // Get the references to the buttons
            val btnUpdate = bottomSheetDialog.findViewById<Button>(R.id.btn_update)
            val btnDelete = bottomSheetDialog.findViewById<Button>(R.id.btn_delete)

            // Set the click listeners for the buttons
            btnUpdate?.setOnClickListener {
                // Handle update action
                val i = Intent(this,UpdatePasswordActivity::class.java)
                i.putExtra("EMAIL_DATA", extraEmail)
                i.putExtra("USERNAME_DATA", userName)
                startActivity(i)
            }

            btnDelete?.setOnClickListener {
                // Handle update action
                val id = db.getUserID(extraEmail ?: "")?.toInt() ?: 0

                val user = User(id = id)

                db.deleteUser(user)
                startActivity(Intent(this, LoginActivity::class.java))

            }

            // Show the BottomSheetDialog
            bottomSheetDialog.show()
        }
    }
}