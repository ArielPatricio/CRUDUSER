package com.example.cruduser

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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




}