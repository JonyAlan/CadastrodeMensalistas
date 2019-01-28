package com.example.cadastrodemensalistas;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListaMensalisActivity extends AppCompatActivity {



    private ListView listaView;
    private MensalistaDAO dao;
    private List<Mensalista> mensalistas;
    private List<Mensalista> mensalistasFiltrados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mensalis);

        listaView = findViewById(R.id.listMensalis);
        dao = new MensalistaDAO(this);
        mensalistas = dao.obtertodos();
        mensalistasFiltrados.addAll(mensalistas);
        mensalistasFiltrados.clear();
        ArrayAdapter<Mensalista> adaptador = new ArrayAdapter<Mensalista>(this,android.R.layout.simple_list_item_1, mensalistasFiltrados);
        listaView.setAdapter(adaptador);

        registerForContextMenu(listaView); //quando um list view for pressionado ele exibe a lista de menu


    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraMensalista(s);
                return false;
            }
        });
        return true;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);

    }


    public void procuraMensalista(String nome){

        mensalistasFiltrados.clear();
        for(Mensalista a : mensalistas){
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())){
                mensalistasFiltrados.add(a);
            }
        }


        listaView.invalidateViews();
    }


    public  void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //obtem a id da lista

            final Mensalista mensalistaExcluir = mensalistasFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir o(a) mensalista ?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mensalistasFiltrados.remove(mensalistaExcluir);
                        mensalistas.remove(mensalistaExcluir);
                        dao.excluir(mensalistaExcluir);
                        listaView.invalidateViews();

                    }
                }).create();

        dialog.show();
    }

    public void atualizar (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //qual posição do click

        final Mensalista mensalistaAtualizar = mensalistasFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class); //chama a tela de cadastro
        it.putExtra("mensalista",mensalistaAtualizar);
        startActivity(it);

    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);

    }
    @Override
    public void onResume(){
        mensalistasFiltrados.clear();
        super.onResume();
        mensalistas = dao.obtertodos();
        mensalistasFiltrados.addAll(mensalistas);
        listaView.invalidateViews();
    }


}
