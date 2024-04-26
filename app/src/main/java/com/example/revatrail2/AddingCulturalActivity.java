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

public class AddingCulturalActivity extends AppCompatActivity {

    EditText name,description,link;
    Button add;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_cultural);

        name = findViewById(R.id.cultural_name_entry);
        description = findViewById(R.id.cultural_description_entry);
        link = findViewById(R.id.cultural_link_entry);
        add = findViewById(R.id.add_cultural_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Cultural");

                String cultname = name.getText().toString();
                String cultdesc = description.getText().toString();
                String cultlink = link.getText().toString();

                if(!cultlink.isEmpty()){
                    HelperClass helper = new HelperClass(cultname,cultdesc,cultlink);
                    reference.child(cultname).setValue(helper);

                    Toast.makeText(getApplicationContext(),"added successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddingCulturalActivity.this,CulturalActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"link must be provided",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}