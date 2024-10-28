package com.example.diariodeclasse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.diariodeclasse.AddEditActivity
import com.example.diariodeclasse.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var alunosList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewAlunos)
        fab = findViewById(R.id.fab)

        alunosList = loadAlunos()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alunosList)
        listView.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val aluno = alunosList[position]
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra("aluno", aluno)
            startActivity(intent)
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val aluno = alunosList[position]
            AlertDialog.Builder(this).apply {
                setTitle("Excluir Aluno")
                setMessage("Deseja excluir $aluno?")
                setPositiveButton("Sim") { _, _ ->
                    deleteAluno(aluno)
                }
                setNegativeButton("Não", null)
            }.show()
            true
        }
    }

    private fun loadAlunos(): MutableList<String> {
        val sharedPreferences = getSharedPreferences("DiarioNotas", Context.MODE_PRIVATE)
        return sharedPreferences.all.keys.toMutableList()
    }

    private fun deleteAluno(aluno: String) {
        val sharedPreferences = getSharedPreferences("DiarioNotas", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(aluno).apply()
        alunosList.remove(aluno)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Aluno $aluno excluído", Toast.LENGTH_SHORT).show()
    }
}
