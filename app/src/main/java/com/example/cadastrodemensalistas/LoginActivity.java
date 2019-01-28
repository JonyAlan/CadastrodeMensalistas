package com.example.cadastrodemensalistas;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//essa linha retira o TitleBar da Activity
        setContentView(R.layout.activity_login);

    }

    public void teste(View view){

        TextView tUser = (TextView) findViewById(R.id.editUser); // vincula os campos do layout com as variaveis
        TextView tSenha = (TextView) findViewById(R.id.editSenha);
        String user = tUser.getText().toString();
        String senha = tSenha.getText().toString();

        if(user.equals("Jony"))
            if(senha.equals("12345")){
                Intent irTela  = new Intent(LoginActivity.this,ListaMensalisActivity.class);// intenção de ir pra outra tela
                startActivity(irTela);
        }
        else{
                Toast.makeText(this, "Login ou senha invalido", Toast.LENGTH_SHORT).show();
            }

    }


}
