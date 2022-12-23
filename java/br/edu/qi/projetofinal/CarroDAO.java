package br.edu.qi.projetofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private SQLiteDatabase bd_carro;
    private ConexaoBD conexaoBD;

    public CarroDAO(Context context){
        this.conexaoBD = new ConexaoBD(context);
        this.bd_carro = conexaoBD.getWritableDatabase();
    }

    public ContentValues gerarValores(Carro objCarro){
        ContentValues values = new ContentValues();

        values.put("nome",objCarro.getNome());
        values.put("modelo",objCarro.getModelo());
        values.put("cor",objCarro.getCor());
        values.put("teto",objCarro.getTeto());

        return values;
    }

    public void cadastrarCarro(Carro objCarro){

        bd_carro.insert("tb_carro",null,this.gerarValores(objCarro));
    }

    public List<Carro> consultarCarrosBD(){
        List<Carro> listaCarros = new ArrayList<>();

        Cursor cursor = this.bd_carro.query("tb_carro",new String[] {"id","nome","modelo","cor","teto"},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            Carro objCarro = new Carro();

            objCarro.setId(cursor.getInt(0));
            objCarro.setNome(cursor.getString(1));
            objCarro.setModelo(cursor.getString(2));
            objCarro.setCor(cursor.getString(3));
            objCarro.setTeto(cursor.getString(4));

            listaCarros.add(objCarro);
        }
        return listaCarros;
    }

    public void excluirProdutoBD(Carro objCarro){
        this.bd_carro.delete("tb_carro", "id=?",
                new String[]{String.valueOf(objCarro.getId())});
    }

    public void alterarCarroBD(Carro objCarro){
        this.bd_carro.update("tb_carro",
                gerarValores(objCarro),"id=?",
                new String[]{String.valueOf(objCarro.getId())});
    }

}
