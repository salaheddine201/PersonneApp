package com.example.personne_project_cc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class view extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("Project",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from personne",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int prenom = c.getColumnIndex("prenom");
        int age = c.getColumnIndex("age");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final  ArrayList<Personne> stud = new ArrayList<Personne>();


        if(c.moveToFirst())
        {
            do{
                Personne stu = new Personne();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.prenom = c.getString(prenom);
                stu.age = c.getString(age);
                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(name) + " \t "  + c.getString(prenom) + " \t "  + c.getString(age) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();



        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                Personne stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(),Edit.class);
                i.putExtra("id",stu.id);
                i.putExtra("name",stu.name);
                i.putExtra("prenom",stu.prenom);
                i.putExtra("age",stu.age);
                startActivity(i);

            }
        });

    }
}