package com.example.cruduser

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private const val DATABASE_NAME = "userapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "User"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_SENHA = "senha"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_TELEFONE = "telefone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
      val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NOME TEXT, $COLUMN_SENHA TEXT, $COLUMN_EMAIL TEXT, $COLUMN_TELEFONE TEXT)"
      db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(user: User){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, user.nome)
            put(COLUMN_SENHA, user.senha)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_TELEFONE, user.telefone)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllUsers() : List<User> {
        val usersList = mutableListOf<User>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
            val senha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENHA))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            val telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE))

            val user = User(id, nome, senha, email, telefone)
            usersList.add(user)
        }
        cursor.close()
        db.close()
        return usersList
    }

    fun updateUser(user: User){
     val db = writableDatabase
     val values = ContentValues().apply {
         put(COLUMN_NOME, user.nome)
         put(COLUMN_SENHA, user.senha)
         put(COLUMN_EMAIL, user.email)
         put(COLUMN_TELEFONE, user.telefone)
     }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(user.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()

    }

    fun getUserById(userId: Int): User{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $userId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
        val senha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENHA))
        val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
        val telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE))

        cursor.close()
        db.close()
        return User(id, nome, senha, email, telefone)


    }

    fun deleteUser(userId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(userId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }




}