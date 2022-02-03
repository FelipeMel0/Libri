package com.example.libri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import database.SQLHelper;

public class CadastroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        SQLHelper.getINSTANCE(CadastroUsuarioActivity.this);

    }
}