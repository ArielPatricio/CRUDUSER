package com.example.cruduser

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruduser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: UserDatabaseHelper
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDatabaseHelper(this)
        usersAdapter = UsersAdapter(db.getAllUsers(), this)

        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = usersAdapter


        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onResume(){
        super.onResume()
        usersAdapter.refreshData(db.getAllUsers())
    }

}