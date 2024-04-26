package com.example.revatrail2;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> implements AdapterView.OnItemClickListener {
    private  final android.content.Context mContext;
    private final List<Item> mItemList;

    private OnItemClickListener onItemClickListener;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ItemAdapter(android.content.Context context, List<Item> items) {
        super(context, R.layout.list_item, items);
        mContext = context;
        mItemList = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        Item currentItem = mItemList.get(position);

        TextView nameTextView = listItem.findViewById(R.id.name_text_view);
        nameTextView.setText(currentItem.getName());

        TextView descTextView = listItem.findViewById(R.id.description_text_view);
        descTextView.setText(currentItem.getDescription());

        TextView linkTextView = listItem.findViewById(R.id.link_text_view);
        linkTextView.setText(currentItem.getLink());

        // Set OnClickListener to the linkTextView
        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = currentItem.getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(intent);
            }
        });

        return listItem;
    }
}
