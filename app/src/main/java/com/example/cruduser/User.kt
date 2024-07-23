package com.example.cruduser

data class User(val id: Int, val nome: String, val senha: String, val email: String, val telefone: String){
    override fun toString(): String {
        return "User(nome='$nome')"
    }
}
