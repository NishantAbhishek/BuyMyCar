package com.example.buymycar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class ConfirmBooking extends AppCompatActivity {
    private EditText name,contact;
    private TextView price;
    private ImageView img;
    private Button book;
    private TextView description;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_confirm);
        name = findViewById(R.id.cf_buyerName);
        contact = findViewById(R.id.cf_buyerPhone);
        price = findViewById(R.id.cf_carPrice);
        img = findViewById(R.id.cf_carImage);
        description = findViewById(R.id.cf_carModel);
        book = findViewById(R.id.cfbutton_buy);




        Intent in = getIntent();
        final String descriptionm = in.getStringExtra("Description");
        final String prices  = in.getStringExtra("Price");
        final String imageUrl = in.getStringExtra("Image");

        description.setText(descriptionm);
        price.setText(prices);

        Glide.with(this).load(imageUrl.toString()).into(img);

//        Glide.with(context).load(carBought.get(position).getImageUrl()).into(holder.img);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending your request");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                bookingCar(prices,descriptionm,imageUrl,contact.getText().toString(),name.getText().toString());
            }
        });



    }

    private void bookingCar(String price,String descrption,String imageUrl,String Contact,String name){


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Buyers");
        String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        String Buyer_node = currentDateTime + "_"+ name + Contact;

        HashMap<String,String> hashData = new HashMap<>();
        hashData.put("Contact",Contact);
        hashData.put("Description",descrption);
        hashData.put("Price",price);
        hashData.put("ImageUrl",imageUrl);
        hashData.put("Name",name);

        reference.child(Buyer_node).setValue(hashData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(ConfirmBooking.this,"Your requst has been sent",Toast.LENGTH_SHORT).show();

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(ConfirmBooking.this,"Please check the internet connection",Toast.LENGTH_SHORT).show(); }

            }
        });



    }




}
