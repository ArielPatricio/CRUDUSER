package com.example.cruduser
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cruduser.databinding.ActivityUpdateUserBinding

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var db: UserDatabaseHelper
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = UserDatabaseHelper(this)

        userId = intent.getIntExtra("user_id", -1)
        if(userId == -1){
            finish()
            return
        }


        val user = db.getUserById(userId)
        binding.updateusername.setText(user.nome)
        binding.updatepassword.setText(user.senha)
        binding.updateemail.setText(user.email)
        binding.updatetelefone.setText(user.telefone)


        binding.updatesaveButton.setOnClickListener {
            val newNome = binding.updateusername.text.toString()
            val newSenha = binding.updatepassword.text.toString()
            val newEmail = binding.updateemail.text.toString()
            val newTelefone = binding.updatetelefone.text.toString()
            val updatedUser = User(userId, newNome, newSenha, newEmail, newTelefone)
            db.updateUser(updatedUser)
            finish()
            Toast.makeText(this, "Usuário atualizado com sucesso", Toast.LENGTH_SHORT).show()

        }




    }
}