package com.example.libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.DateFormat;

public class CadastroLivroActivity extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtDescricao;
    private EditText txtFoto;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescricao = findViewById(R.id.txtLivroDescricao);
        txtFoto = findViewById(R.id.txtLivroFoto);
        btnCadastrar = findViewById(R.id.btnCadastrarLivro);

        btnCadastrar.setOnClickListener(view -> {

            if (!validate()){
                Toast.makeText(this, "Preencha todos os campos, brother", Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cadastroLivroTitulo))
                    .setMessage(getString(R.string.mensagem_cadastro_livro))
                    .setPositiveButton(R.string.salvar, (dialog1, which)->{
                        //Ação do PositiveButton
                        String titulo = txtTitulo.getText().toString();
                        String descricao = txtDescricao.getText().toString();
                        String foto = txtFoto.getText().toString();

                        // Data de inserção do livro

                        DateFormat df = new DateFormat();
                        String created_date = df.getDateFormat();

                        boolean cadastroLivro = SQLHelper.getINSTANCE(CadastroLivroActivity.this).addBook(1, titulo, descricao, foto, created_date);

                        //Opções de diálogo

                        if (cadastroLivro){
                            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this, "Houve um erro ao realizar o cadastro", Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, which)->{}).create();

            dialog.show();



        });

    }

    //Menu

    //Inflate do menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    /* Ações do menu */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("MENUITEM-", String.valueOf(item.getItemId()));

        switch (item.getItemId()){

            case R.id.menu_cadastrar_livro:
                startActivity(new Intent(this, CadastroLivroActivity.class));
                break;

            case R.id.menu_feed_livro:
                startActivity(new Intent(this, FeedLivroActivity.class));
                break;

            case R.id.menu_sair:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    //Método de validação
    private boolean validate() {

        return (
                !txtTitulo.getText().toString().isEmpty() &&
                !txtDescricao.getText().toString().isEmpty() &&
                !txtFoto.getText().toString().isEmpty()
        );

    }
}