package com.example.cruduser

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(private var users: List<User>, context: Context) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {


    private val db: UserDatabaseHelper = UserDatabaseHelper(context)

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nomeTextView: TextView = itemView.findViewById(R.id.nomeTextView)
        val senhaTextView: TextView = itemView.findViewById(R.id.senhaTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        val telefoneTextView: TextView = itemView.findViewById(R.id.telefoneTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.nomeTextView.text = user.nome
        holder.senhaTextView.text = user.senha
        holder.emailTextView.text = user.email
        holder.telefoneTextView.text = user.telefone

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateUserActivity::class.java).apply {
                putExtra("user_id", user.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteUser(user.id)
            refreshData(db.getAllUsers())
            Toast.makeText(holder.itemView.context, "Usuário deletado com sucesso", Toast.LENGTH_SHORT).show()
        }


    }

    fun refreshData(newUsers: List<User>){
        users = newUsers
        notifyDataSetChanged()
    }

}