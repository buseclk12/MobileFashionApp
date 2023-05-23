package com.example.hw2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity implements MyInterface{
    private static MyInterface myInterface;
    Button goBack;
    RecyclerView recyclerViewProduct;
    private List<Product> recyclerItemValues;
    private TextView itemCountTextView;
    //YENİ YAZILDI
    private Button userProfileButton;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private Spinner spinner;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        recyclerViewProduct = findViewById(R.id.recyclerViewProduct);
        //YENİ YAZILDI
        userProfileButton = findViewById(R.id.userProfile);
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewProduct.setLayoutManager(layoutManager);

        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(this,ProductSys.products);
        recyclerViewProduct.setAdapter(adapter);
        ProductSys.prepareData();

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserProfileDialog(username,password);
            }


        });
        goBack = findViewById(R.id.logOut);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setInterfaceInstance(MainActivity2.this);
               startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

    }
    public static void setInterfaceInstance(Context context) {
        myInterface = (MyInterface) context;
    }

    public void onProductListUpdated(int itemCount) {
        itemCountTextView.setText(String.valueOf(itemCount));
    }

    //YENİ YAZILDI
    private void showUserProfileDialog(String username, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Profile");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Username: ").append(username).append("\n");
        stringBuilder.append("E-mail: ").append(password).append("\n");

        builder.setMessage(stringBuilder.toString());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void callBack(String data) {
        Toast.makeText(this, "Data received" + itemCountTextView, Toast.LENGTH_LONG).show();
    }
}
