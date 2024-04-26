package com.example.revatrail2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingSportsActivity extends AppCompatActivity {

    EditText name,description,link;
    Button add;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_sports);

        name = findViewById(R.id.sports_name_entry);
        description = findViewById(R.id.sports_description_entry);
        link = findViewById(R.id.sports_link_entry);
        add = findViewById(R.id.add_sports_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Sports");

                String sportname = name.getText().toString();
                String sportdesc = description.getText().toString();
                String sportlink = link.getText().toString();

                if(!sportlink.isEmpty()){
                    HelperClass helper = new HelperClass(sportname,sportdesc,sportlink);
                    reference.child(sportname).setValue(helper);

                    Toast.makeText(getApplicationContext(),"added succesfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddingSportsActivity.this,SportsActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"link must be provided",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}