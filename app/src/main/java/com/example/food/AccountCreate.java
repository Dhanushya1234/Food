package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountCreate extends AppCompatActivity {


    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);

        EditText username = findViewById(R.id.username1);
        EditText password = findViewById(R.id.password1);
        EditText repassword = findViewById(R.id.confirmpassword);
        Button submit=findViewById(R.id.submit);

        db=new DbHelper(AccountCreate.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(AccountCreate.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkUser = db.checkusername(user);
                        if (checkUser == false) {
                            Boolean insert=db.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(AccountCreate.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AccountCreate.this, MenuActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AccountCreate.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AccountCreate.this,"User Already EXist",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AccountCreate.this,"Password not Matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}