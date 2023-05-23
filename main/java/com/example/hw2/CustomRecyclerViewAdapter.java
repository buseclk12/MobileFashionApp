package com.example.hw2;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerViewAdapter extends
        RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder> {

    private Context context;

    private ArrayList<Product> recyclerItemValues;
    private ProductListListener listener;

    public CustomRecyclerViewAdapter(Context context, ArrayList<Product> values){
        this.context = context;
        this.recyclerItemValues = values;
    }



    @NonNull
    @Override
    public CustomRecyclerViewAdapter.CustomRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(context);

        View itemView = inflator.inflate(R.layout.homepage, viewGroup, false);

        CustomRecyclerViewItemHolder mViewHolder = new CustomRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {
        final Product curentItem = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tvProductItemName.setText(curentItem.getName());
        //myRecyclerViewItemHolder.tvProductItemPrice.setText(curentItem.getPrice()+"");
        //myRecyclerViewItemHolder.imgProductItem.setImageResource(R.mipmap.ic_launcher);

        myRecyclerViewItemHolder.btnProdcutItemDetail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayDialog(curentItem);
                        Toast.makeText(context, curentItem.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class CustomRecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView tvProductItemName,tvProductItemPrice;
        //ImageView imgProductItem;
        Button btnProdcutItemDetail;
        LinearLayout parentItemLayout;
        public CustomRecyclerViewItemHolder(View itemView) {
            super(itemView);
            tvProductItemName = itemView.findViewById(R.id.productItemName);
            //imgProductItem = itemView.findViewById(R.id.imgProductItem);
            btnProdcutItemDetail = itemView.findViewById(R.id.btnProdcutItemDetail);
            parentItemLayout = itemView.findViewById(R.id.parentItemLayout);
        }
    }
    public void displayDialog(Product p){
        Dialog d = new Dialog(context);
        d.setContentView(R.layout.dialog);
        //TextView itemCount = d.findViewById(R.id.itemCount);
        TextView tvDialogResult = d.findViewById(R.id.tvDailogResult);
        Button btnDialogClose = d.findViewById(R.id.btnDialogClose);
        Spinner count = d.findViewById(R.id.countProduct);
        //yeni
        ImageView imageViewProduct = d.findViewById(R.id.imageViewProduct);

        tvDialogResult.setText(p.toString());
        //yeni
        imageViewProduct.setImageResource(p.getImageResId());
        btnDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                Toast.makeText(context, "Items added to the shopping list", Toast.LENGTH_SHORT).show();


            }
        });
        d.create();
        d.show();
    }
    public void setListener(ProductListListener listener) {
        this.listener = listener;
    }
    private void updateItemCount() {
        if (listener != null) {
            int itemCount = recyclerItemValues.size();
            listener.onProductListUpdated(itemCount);
        }
    }
}


