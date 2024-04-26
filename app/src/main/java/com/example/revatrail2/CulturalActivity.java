package com.example.revatrail2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CulturalActivity extends AppCompatActivity {
    private ListView itemListView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultural);

        itemListView = findViewById(R.id.item_list_view);

        List<Item> itemList = new ArrayList<>();
        itemAdapter = new ItemAdapter(this,itemList);

        itemListView.setAdapter(itemAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef =database.getReference("Cultural");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemList.clear();

                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
                    Item item =itemSnapshot.getValue(Item.class);

                    itemList.add(item);
                }

                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("CulturalActivity","Database error: "+error.getMessage());
            }
        });

        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                startActivity(intent);
            }
        });
    }
}