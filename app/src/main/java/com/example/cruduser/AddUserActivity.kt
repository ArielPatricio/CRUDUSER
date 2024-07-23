package com.example.cruduser

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cruduser.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding
    private lateinit var db: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = UserDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val nome = binding.username.text.toString()
            val senha = binding.password.text.toString()
            val email = binding.email.text.toString()
            val telefone = binding.telefone.text.toString()
            val user = User(0, nome, senha, email, telefone)
            db.insertUser(user)
            finish()
            Toast.makeText(this, "Usuário salvo com sucesso", Toast.LENGTH_SHORT).show()



        }









    }
}