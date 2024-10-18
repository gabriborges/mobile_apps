package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referenciar os campos de email e senha
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val loginButton = findViewById<Button>(R.id.button)

        // Ação do botão "Entrar"
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verificar se os campos estão preenchidos
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Criar uma Intent para navegar para a ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)

                // Passar os dados de email e senha para a próxima activity
                intent.putExtra("email", email)
                intent.putExtra("password", password)

                // Iniciar a ProfileActivity
                startActivity(intent)
            } else {
                // Exibir uma mensagem de erro se os campos estiverem vazios
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
