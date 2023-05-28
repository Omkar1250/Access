package com.example.contentprovideraccessing;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private static final Uri CONTENT_URI =
            Uri.parse("content://com.demo.user.provider/users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @SuppressLint("SetTextI18n")
    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView = findViewById(R.id.res);
        // creating a cursor object of the content URI
        try (Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null,
                null)) {
            // iteration of the cursor to print the whole table
            if (cursor.moveToFirst()) {
                StringBuilder strBuild = new StringBuilder();
                do {
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    strBuild.append("\n").append(id).append("-").append(name);
                } while (cursor.moveToNext());
                resultView.setText(strBuild.toString());
            } else {
                resultView.setText("No Records Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultView.setText("Error retrieving data");
        }
    }
}