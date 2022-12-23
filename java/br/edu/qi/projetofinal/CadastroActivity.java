package br.edu.qi.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtModelo, edtCor, edtTeto;
    private Button btnAdd, btnListar;
    private CarroDAO objCarroDAO;
    private Carro objCarro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        edtNome = findViewById(R.id.edtNome);
        edtCor = findViewById(R.id.edtCor);
        edtModelo = findViewById(R.id.edtModelo);
        edtTeto = findViewById(R.id.edtTeto);
        btnAdd = findViewById(R.id.btnAdd);
        btnListar = findViewById(R.id.btnListar);
        objCarroDAO = new CarroDAO(this);


        Intent i = getIntent();
        if(i.hasExtra("carro")){
            objCarro = (Carro) i.getSerializableExtra("carro");

            edtNome.setText(objCarro.getNome());
            edtModelo.setText(objCarro.getModelo());
            edtCor.setText(objCarro.getCor());
            edtTeto.setText(objCarro.getTeto());
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(objCarro == null) {
                    Carro objCarro = new Carro();

                    objCarro.setNome(edtNome.getText().toString());
                    objCarro.setModelo(edtModelo.getText().toString());
                    objCarro.setCor(edtCor.getText().toString());
                    objCarro.setTeto(edtTeto.getText().toString());

                    objCarroDAO.cadastrarCarro(objCarro);
                    Toast.makeText(CadastroActivity.this, "Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    objCarro.setNome(edtNome.getText().toString());
                    objCarro.setModelo(edtModelo.getText().toString());
                    objCarro.setCor(edtCor.getText().toString());
                    objCarro.setTeto(edtTeto.getText().toString());
                    objCarroDAO.alterarCarroBD(objCarro);
                    Toast.makeText(CadastroActivity.this, "Alterado com Sucesso!", Toast.LENGTH_SHORT).show();
                }



                edtNome.setText("");
                edtCor.setText("");
                edtModelo.setText("");
                edtTeto.setText("");
                edtNome.setText("");

            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CadastroActivity.this,ListaActicity.class);
                startActivity(i);
            }
        });


    }


}