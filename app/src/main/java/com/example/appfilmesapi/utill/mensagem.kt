package com.example.appfilmesapi.utill

import android.app.Activity
import android.widget.Toast

fun Activity.exibirMensagem(mensagem: String) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}