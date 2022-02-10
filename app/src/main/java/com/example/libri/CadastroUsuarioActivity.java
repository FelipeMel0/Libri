package com.example.libri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.DateFormat;

public class CadastroUsuarioActivity extends AppCompatActivity {

    //Representação dos campos da Activity
    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtEmail;
    private EditText txtLogin;
    private EditText txtSenha;
    private Button btnCadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        //Captura dos componentes gráficos da activity

        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrarUsuario = findViewById(R.id.btnCadastrarUsuario);

        // Tratamento de evento de clique do botão

        btnCadastrarUsuario.setOnClickListener(view -> { //O corpo do Lambda trata a ação que será executado após o clique
            if (!validate()){
                Toast.makeText(this, "Preencha todos os campos, brother", Toast.LENGTH_SHORT).show();
                return;
            }

            //Processo de gravação do usuário
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cadastroUsuarioTitulo))
                    .setMessage(getString(R.string.mensagem_cadastro_usuario))
                    .setPositiveButton(R.string.salvar, (dialog1, which)->{
                        //Ação do PositiveButton
                        String nome = txtNome.getText().toString();
                        String sobrenome = txtSobrenome.getText().toString();
                        String email = txtEmail.getText().toString();
                        String login = txtLogin.getText().toString();
                        String senha = txtSenha.getText().toString();

                        // Data de inserção do usuário

                        DateFormat df = new DateFormat();
                        String created_date = df.getDateFormat();

                        boolean cadastroUsuario = SQLHelper.getINSTANCE(this).addUser(nome, sobrenome, email, login, senha, created_date);

                        //Opções de diálogo

                        if (cadastroUsuario){
                            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this, "Houve um erro ao realizar o cadastro", Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton(R.string.cancelar, (dialog1, which)->{}).create();

            dialog.show();

        });

    }

    //Método de validação
    private boolean validate() {

        return (
                !txtNome.getText().toString().isEmpty() &&
                !txtSobrenome.getText().toString().isEmpty() &&
                !txtEmail.getText().toString().isEmpty() &&
                !txtLogin.getText().toString().isEmpty() &&
                !txtSenha.getText().toString().isEmpty()
        );

    }

}