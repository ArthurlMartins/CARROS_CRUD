package br.edu.qi.projetofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoBD extends SQLiteOpenHelper {
    private static final String name = "bd_carro";
    private static final int version = 1;

    public ConexaoBD(@Nullable Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase bd_carro) {
        bd_carro.execSQL("CREATE TABLE tb_carro(id Integer not null primary key autoincrement," +
                "nome varchar(255) not null, modelo varchar(255), cor varchar(255), teto varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
