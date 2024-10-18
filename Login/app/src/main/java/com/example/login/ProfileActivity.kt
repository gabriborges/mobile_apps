package com.example.login

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val emailTextView = findViewById<TextView>(R.id.textViewEmail)
        val passwordTextView = findViewById<TextView>(R.id.textViewPassword)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        emailTextView.text = email ?: "Email não fornecido"
        passwordTextView.text = password ?: "Senha não fornecida"

        val backButton = findViewById<Button>(R.id.buttonBackToLogin)

        backButton.setOnClickListener {
            finish()
        }
    }
}
