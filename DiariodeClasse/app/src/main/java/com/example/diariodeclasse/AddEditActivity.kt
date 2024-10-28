package com.example.diariodeclasse

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddEditActivity : AppCompatActivity() {

    private lateinit var editTextNomeAluno: EditText
    private lateinit var editTextNota1: EditText
    private lateinit var editTextNota2: EditText
    private lateinit var editTextNota3: EditText
    private lateinit var buttonSalvar: Button

    private var isEditMode = false
    private var aluno: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        editTextNomeAluno = findViewById(R.id.editTextNomeAluno)
        editTextNota1 = findViewById(R.id.editTextNota1)
        editTextNota2 = findViewById(R.id.editTextNota2)
        editTextNota3 = findViewById(R.id.editTextNota3)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        aluno = intent.getStringExtra("aluno")
        isEditMode = aluno != null

        if (isEditMode) {
            loadAlunoData(aluno!!)
        }

        buttonSalvar.setOnClickListener {
            saveAluno()
        }
    }

    private fun loadAlunoData(aluno: String) {
        val sharedPreferences = getSharedPreferences("DiarioNotas", MODE_PRIVATE)
        val alunoData = sharedPreferences.getString(aluno, null)

        alunoData?.let {
            val notas = it.split(",")
            editTextNomeAluno.setText(aluno)
            editTextNota1.setText(notas[0])
            editTextNota2.setText(notas[1])
            editTextNota3.setText(notas[2])
        }
    }

    private fun saveAluno() {
        val nome = editTextNomeAluno.text.toString()
        val nota1 = editTextNota1.text.toString()
        val nota2 = editTextNota2.text.toString()
        val nota3 = editTextNota3.text.toString()

        if (nome.isEmpty() || nota1.isEmpty() || nota2.isEmpty() || nota3.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val sharedPreferences = getSharedPreferences("DiarioNotas", MODE_PRIVATE)
        sharedPreferences.edit().putString(nome, "$nota1,$nota2,$nota3").apply()

        Toast.makeText(this, "Aluno salvo", Toast.LENGTH_SHORT).show()
        finish()
    }
}
