package com.synnlabz.registration_login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText Name , IndexNo , Email , Mobile , GPA , Password , Repassword;
    Button btnSubmit;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        Name = findViewById(R.id.name);
        IndexNo = findViewById(R.id.index);
        Email = findViewById(R.id.email);
        Mobile = findViewById(R.id.mobile);
        GPA = findViewById(R.id.gpa);
        Password = findViewById(R.id.password);
        Repassword = findViewById(R.id.repassword);

        btnSubmit = findViewById(R.id.submit);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.name, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(this, R.id.mobile, RegexTemplate.TELEPHONE, R.string.err_tel);
        awesomeValidation.addValidation(this, R.id.email, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(this, R.id.index, "[1][6][0-9]{4}[a-zA-Z]", R.string.invalid_phone); //meka hadapan

        final String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(this, R.id.password, regexPassword, R.string.err_password);
        // to validate a confirmation field (don't validate any rule other than confirmation on confirmation field)
        awesomeValidation.addValidation(this, R.id.repassword, R.id.password, R.string.err_password_confirmation);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(),"Form Validation Successfull",Toast.LENGTH_SHORT).show();
                    String name = Name.getText().toString();
                    String indexno = IndexNo.getText().toString();
                    String email = Email.getText().toString();
                    String mobile = Mobile.getText().toString();
                    String gpa = GPA.getText().toString();
                    String password = Password.getText().toString();
                    String repassword = Repassword.getText().toString();

                    if (name.equals("") || indexno.equals("") || email.equals("") || password.equals("") || indexno.equals("")) {
                        Toast.makeText(getApplicationContext(),"Files are Empty",Toast.LENGTH_SHORT).show();
                    }else {
                        if (password.equals(repassword)){
                            Boolean checkemail = db.checkemail(email);
                            if (checkemail==true){
                                Boolean insert =  db.insert(name ,email ,indexno ,mobile ,gpa ,password);
                                if (insert==true){
                                    Toast.makeText(getApplicationContext(),"Registration is Successfull",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Form Validation Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void goToLogin(View view) {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }
}
