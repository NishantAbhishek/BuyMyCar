package com.example.buymycar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.buymycar.ConfirmBooking;
import com.example.buymycar.Model.Cars;
import com.example.buymycar.R;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    public Context mContext;
    public List<Cars> mPost;

    public CarsAdapter(Context mContext, List<Cars> mPost) {
         this.mContext = mContext;
        this.mPost = mPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car,parent,false);
        return new CarsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.description.setText(mPost.get(position).getDescription());
        Glide.with(mContext).load(mPost.get(position).getPostimage()).into(holder.carImage);
        holder.price.setText(mPost.get(position).getPrice());

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, ConfirmBooking.class);
                in.putExtra("Description",mPost.get(position).getDescription());
                in.putExtra("Price",mPost.get(position).getPrice());
                in.putExtra("Image",mPost.get(position).getPostimage());
                mContext.startActivity(in);
            }
        });

    }




    @Override
    public int getItemCount() {
        return mPost.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView carImage;
        public TextView description,price;
        public Button buy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImage = itemView.findViewById(R.id.item_car);
            description = itemView.findViewById(R.id.item_description);
            price = itemView.findViewById(R.id.item_price);
            buy = itemView.findViewById(R.id.item_buy);

        }
    }

}
