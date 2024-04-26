package com.example.revatrail2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingTechnicalActivity extends AppCompatActivity {

    EditText name,description,link;
    Button add;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_technical);

        name = findViewById(R.id.technical_name_entry);
        description = findViewById(R.id.technical_description_entry);
        link = findViewById(R.id.technical_link_entry);
        add = findViewById(R.id.add_technical_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("technical");

                String techname = name.getText().toString();
                String techdesc = description.getText().toString();
                String techlink = link.getText().toString();

                if(!techlink.isEmpty()){
                    HelperClass helper = new HelperClass(techname,techdesc,techlink);
                    reference.child(techname).setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(getApplicationContext(),"added successfully",Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(AddingTechnicalActivity.this,TechnicalActivity.class);
                               startActivity(intent);
                               finish();
                           }
                           else{
                               Toast.makeText(getApplicationContext(),"error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                           }
                        }
                    });



                }else{
                    Toast.makeText(getApplicationContext(),"link must be provided", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}