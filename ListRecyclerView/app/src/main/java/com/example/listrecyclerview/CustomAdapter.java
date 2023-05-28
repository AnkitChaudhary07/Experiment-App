package com.example.listrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {
    private String[] arr;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull String[] arr) {
        super(context, resource, arr);
        this.arr = arr;
    }
    //Constructor

    @NonNull
    @Override //Override getView
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ankit_layout,parent,false);
        TextView tv = convertView.findViewById(R.id.textView);
        tv.setText(getItem(position));
        return convertView;
    }

    @Nullable
    @Override //Override getItem
    public String getItem(int position) {
        return arr[position];
    }
}
