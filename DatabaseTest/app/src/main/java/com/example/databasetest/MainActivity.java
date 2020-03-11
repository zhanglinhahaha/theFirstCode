package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new MyDatabaseHelper(this,
                "BookStore.db", null, 3);
        Button createdb = (Button) findViewById(R.id.create_database);
        createdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.getWritableDatabase();
            }
        });

        //insert data
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", "454");
                values.put("price", "16.96");
                database.insert("Book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", "510");
                values.put("price", "19.95");
                database.insert("Book", null, values);
                Log.d("TAG", "onClick: insert");
            }
        });

        //update data
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                database.update("Book", values, "name = ?",
                        new String[]{"The Da Vinci Code"});
            }
        });

        //delete data
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                database.delete("Book", "pages > ?",
                        new String[]{"500"});
            }
        });

        //query data
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                Cursor cursor = database.query("Book", null, null ,
                        null, null, null, null);
                if(cursor.moveToFirst()) {
                    do {
                       String name = cursor.getString(cursor.getColumnIndex("name"));
                       String author = cursor.getString(cursor.getColumnIndex("author"));
                       int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                       double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("TAG", name + " " + author +
                                " " + pages + " " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
