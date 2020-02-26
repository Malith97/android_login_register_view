package com.synnlabz.registration_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText Indexno , Password;
    Button BtnLogin;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db =  new DatabaseHelper(this);
        Indexno = (EditText)findViewById(R.id.index);
        Password = (EditText)findViewById(R.id.pass);
        BtnLogin = (Button)findViewById(R.id.login);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String indexno = Indexno.getText().toString();
                String password = Password.getText().toString();

                String checkIndexno = db.emailpassword(indexno,password);

                if (checkIndexno!=null){
                    Toast.makeText(getApplicationContext(),"Login is Successfull",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, UserProfile.class);
                    intent.putExtra("Indexno",checkIndexno);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Login is Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(login.this, MainActivity.class);
        startActivity(intent);
    }
}
