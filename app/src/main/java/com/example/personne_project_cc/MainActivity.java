package com.example.personne_project_cc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


        EditText ed1,ed2,ed3;
        Button b1,b2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ed1 = findViewById(R.id.name);
            ed2 = findViewById(R.id.course);
            ed3 = findViewById(R.id.fee);

            b1 = findViewById(R.id.bt1);
            b2 = findViewById(R.id.bt2);

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(getApplicationContext(),view.class);
                    startActivity(i);
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insert();
                }
            });
        }

        public void insert()
        {
            try
            {
                String name = ed1.getText().toString();
                String prenom = ed2.getText().toString();
                String age = ed3.getText().toString();

                SQLiteDatabase db = openOrCreateDatabase("Project", Context.MODE_PRIVATE,null);
                db.execSQL("CREATE TABLE IF NOT EXISTS Personne(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,prenom VARCHAR,age VARCHAR)");

                String sql = "insert into Personne(name,prenom,age)values(?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1,name);
                statement.bindString(2,prenom);
                statement.bindString(3,age);
                statement.execute();
                Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed1.requestFocus();
            }
            catch (Exception ex)
            {
                Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
            }
        }
    }
