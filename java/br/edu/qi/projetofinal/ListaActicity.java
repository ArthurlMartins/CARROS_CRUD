package br.edu.qi.projetofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaActicity extends AppCompatActivity {
    private ListView lstCarros;
    private CarroDAO objCarrosDAO;
    private SearchView icConsultar;
    private List<Carro> todosOsCarros;
    private List<Carro> carrosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_acticity);

        lstCarros = findViewById(R.id.lstCarros);
        objCarrosDAO = new CarroDAO(this);

        todosOsCarros = objCarrosDAO.consultarCarrosBD();
        carrosFiltrados.addAll(todosOsCarros);

        ArrayAdapter adp = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,carrosFiltrados);
        lstCarros.setAdapter(adp);

        registerForContextMenu(lstCarros);

    }

    public void abrirTelaDeCadastro(MenuItem item){
        Intent i = new Intent(ListaActicity.this,CadastroActivity.class);
        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.item,menu);
    }
    public void excluirCarro(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Carro objCarroDeletado = carrosFiltrados.get(menuInfo.position);

        AlertDialog confirmacao = new AlertDialog.Builder(this)
                .setTitle("Cuidado!")
                .setMessage("Você realmente dejesa excluir este item?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todosOsCarros.remove(objCarroDeletado);
                        carrosFiltrados.remove(objCarroDeletado);
                        objCarrosDAO.excluirProdutoBD(objCarroDeletado);
                        lstCarros.invalidateViews();
                    }
                }).create();
                confirmacao.show();
    }
    public void alterarCarro(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Carro objCarroAlterado = carrosFiltrados.get(menuInfo.position);

        Intent i = new Intent(this,CadastroActivity.class);

        i.putExtra("carro", objCarroAlterado);
        startActivity(i);
    }

    }

