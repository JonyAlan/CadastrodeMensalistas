package com.example.cadastrodemensalistas;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MensalistaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public MensalistaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();

    }

    public long inserir(Mensalista mensalista) {
        ContentValues values = new ContentValues();
        values.put("nome", mensalista.getNome());
        values.put("cpf", mensalista.getCpf());
        values.put("telefone", mensalista.getTelefone());
        values.put("modelo", mensalista.getModelo());
        values.put("placa", mensalista.getPlaca());
        return banco.insert("mensalista", null, values);

    }

    public List<Mensalista> obtertodos() {
        List<Mensalista> mensalistas = new ArrayList<>();
        Cursor cursor = banco.query("mensalista", new String[]{"id", "nome", "cpf", "telefone", "modelo", "placa"},
                null, null, null, null, null);

        while (cursor.moveToNext()){
            Mensalista a = new Mensalista();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            a.setModelo(cursor.getString(4));
            a.setPlaca(cursor.getString(5));
            mensalistas.add(a);

        }
            return mensalistas ;

    }

        public void excluir(Mensalista a){
            banco.delete("mensalista","id = ? ",new String[]{a.getId().toString()});
        }

        public void atualizar(Mensalista mensalista){
            ContentValues values = new ContentValues();
            values.put("nome", mensalista.getNome());
            values.put("cpf", mensalista.getCpf());
            values.put("telefone", mensalista.getTelefone());
            values.put("modelo", mensalista.getModelo());
            values.put("placa", mensalista.getPlaca());
            banco.update("mensalista", values,
                    "id = ?",new String[]{mensalista.getId().toString()});



        }




}