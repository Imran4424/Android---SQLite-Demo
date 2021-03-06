package com.imran.android.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

    //        myDatabase.execSQL("INSERT INTO users (name, age) VALUES('Munir', 28)");
    //        myDatabase.execSQL("INSERT INTO users (name, age) VALUES('Imran', 23)");
    //        myDatabase.execSQL("INSERT INTO users (name, age) VALUES('Anik', 26)");

            Cursor cursor = myDatabase.rawQuery("SELECT * FROM users", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", cursor.getString(ageIndex));

                cursor.moveToNext();
            }

            cursor = myDatabase.rawQuery("SELECT * FROM users WHERE age > 25", null);

            nameIndex = cursor.getColumnIndex("name");
            ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            Log.i("Divide", "Showing users who is greater than 25");
            while (!cursor.isAfterLast()) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", cursor.getString(ageIndex));

                cursor.moveToNext();
            }

            cursor = myDatabase.rawQuery("SELECT * FROM users WHERE age > 25 AND name = 'Imran'", null);

            nameIndex = cursor.getColumnIndex("name");
            ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            Log.i("Divide", "Showing users who is greater than 25 and name is Imran");
            while (!cursor.isAfterLast()) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", cursor.getString(ageIndex));

                cursor.moveToNext();
            }

//            myDatabase.execSQL("DELETE FROM users WHERE name  = 'Anik' LIMIT 1");

            cursor = myDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%a%' LIMIT 3", null);

            nameIndex = cursor.getColumnIndex("name");
            ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            Log.i("Divide", "Showing users who name is like '.a.'");
            while (!cursor.isAfterLast()) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", cursor.getString(ageIndex));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}