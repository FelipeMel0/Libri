package com.example.libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.Item;
import model.Livro;

public class FeedLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_livro);
    }

    /* Inflate de menu */
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

    /*Adapter do RecyclerView - Vincula os dados com a estrutura gráfica .xml*/

    class LivroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public List<Item> item; //Atributo que recebe os objetos de "Items"

        public LivroAdapter(List<Item> item){

            this.item = item;

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //Inicializa a classe ViewHolder dentro de adapter

            if(viewType == 0){

                return new LivroAdapter.LivroViewHolder();

            }

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) { //Associa os objetos da ViewHolder aos dados

        }

        @Override
        public int getItemCount() { //Quantidade de itens na contagem
            return 0;
        }

        /*ViewHolder - Define as estruturas de interface. "Envelopa" as estruturas .xml*/

        class LivroViewHolder extends RecyclerView.ViewHolder{

            private TextView textLivroTitulo, textLivroDescricao;
            private int cod_livro;

            public LivroViewHolder(@NonNull View itemView) {
                super(itemView);

                textLivroTitulo = itemView.findViewById(R.id.textLivroTitulo);
                textLivroDescricao = itemView.findViewById(R.id.textLivroDescricao);

            }

            /*Método de set de dados nas TextViews*/

            public void setLivroData(Livro livro){

                textLivroTitulo.setText(livro.getTitulo());
                textLivroDescricao.setText(livro.getDescricao());

            }

        }

        /*Fim do ViewHolder*/

    }

}