package com.example.cadastrodemensalistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText modelo;
    private EditText placa;
    private MensalistaDAO dao;

    private Mensalista mensalista = null; // variavel global para atualizar

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCpf);
        telefone = findViewById(R.id.editTelefone);
        modelo = findViewById(R.id.editModelo);
        placa = findViewById(R.id.editPlaca);
        dao = new MensalistaDAO(this);

        final EditText editTelefone = (EditText) findViewById(R.id.editTelefone);
        editTelefone.addTextChangedListener(Mask.insert("(##)#####-####", editTelefone));
        final EditText editCpf = (EditText) findViewById(R.id.editCpf);
        editCpf.addTextChangedListener(Mask.insert("###.###.###-##", editCpf));
        final EditText editPlaca = (EditText) findViewById(R.id.editPlaca);
        editPlaca.addTextChangedListener(Mask.insert("###-####", editPlaca));

        Intent it = getIntent();
        if (it.hasExtra("mensalista")) {
            mensalista = (Mensalista) it.getSerializableExtra("mensalista");
            nome.setText(mensalista.getNome());
            cpf.setText(mensalista.getCpf());
            telefone.setText(mensalista.getTelefone());
            modelo.setText(mensalista.getModelo());
            placa.setText(mensalista.getPlaca());

        }

    }


    public void salvar(View view) {


        if(mensalista == null) {
            mensalista = new Mensalista();
            mensalista.setNome(nome.getText().toString());
            mensalista.setCpf(cpf.getText().toString());
            mensalista.setTelefone(telefone.getText().toString());
            mensalista.setModelo(modelo.getText().toString());
            mensalista.setPlaca(placa.getText().toString());
            long id = dao.inserir(mensalista);

            Toast.makeText(this, "Novo mensalista adicionado !!!", Toast.LENGTH_SHORT).show();


        }else{
            mensalista.setNome(nome.getText().toString());
            mensalista.setCpf(cpf.getText().toString());
            mensalista.setTelefone(telefone.getText().toString());
            mensalista.setModelo(modelo.getText().toString());
            mensalista.setPlaca(placa.getText().toString());
            dao.atualizar(mensalista);

            Toast.makeText(this, "Mensalista Atualizado", Toast.LENGTH_SHORT).show();
        }

        final EditText editNome = (EditText) findViewById(R.id.editNome);
        final EditText editCpf = (EditText) findViewById(R.id.editCpf);
         final EditText editModelo = (EditText) findViewById(R.id.editModelo);
        final EditText editPlaca = (EditText) findViewById(R.id.editPlaca);
        final EditText editTelefone = (EditText) findViewById(R.id.editTelefone);

        editNome.setText("");
        editCpf.setText("");
        editTelefone.setText("");
        editModelo.setText("");
        editPlaca.setText("");

   }


}