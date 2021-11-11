package com.example.taka24;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {


    ArrayList<Data> adapterArrayListObj;

    public MyAdapter(ArrayList<Data> arrayListObj) {
        this.adapterArrayListObj = arrayListObj;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,null));
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {

        MyViewHolder viewHolderObj = (MyViewHolder) holder;

        Data dataClassObj = adapterArrayListObj.get(position);

        viewHolderObj.title.setText(dataClassObj.getCategory_name());
        //viewHolderObj.imageViewObj.setImageURI(Uri.parse(dataClassObj.getCategory_icon()));

    }

    @Override
    public int getItemCount() {
        return adapterArrayListObj.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageViewObj;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.contentName);
            imageViewObj = itemView.findViewById(R.id.imageForItem);
        }

    }


}
