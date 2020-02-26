package com.synnlabz.registration_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    TextView VName , VIndex , VEmail , VGpa , VContact;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent = getIntent();
        String Indexno = intent.getStringExtra("Indexno");
        VName = findViewById(R.id.view_name);
        VIndex = findViewById(R.id.view_indexno);
        VEmail = findViewById(R.id.view_email);
        VContact = findViewById(R.id.view_mobile);
        VGpa = findViewById(R.id.view_gpa);

        db = new DatabaseHelper(this);
        Cursor cursor = db.alldata(Indexno);

        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                VName.setText(cursor.getString(2));
                VIndex.setText(cursor.getString(0));
                VEmail.setText(cursor.getString(3));
                VContact.setText(cursor.getString(4));
                VGpa.setText(cursor.getString(5));
            }
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(UserProfile.this, login.class);
        startActivity(intent);
    }
}
