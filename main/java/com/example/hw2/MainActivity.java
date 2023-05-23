package com.example.hw2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat gestureDetector;
    EditText usernameEditText, passwordEditText;
    TextView registerTextView;
    CardView cardView;
    private TextView textLast;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);

        //yeni
        textLast = findViewById(R.id.textLast);
        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        usernameEditText = findViewById(R.id.editTextTextPersonName);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        registerTextView = findViewById(R.id.textView2);

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kayıt olma ekranına geçiş yapma!
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        CardView loginCardView = findViewById(R.id.cardView);
        loginCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = sharedPreferences.getInt("user_id", -1);
                String username= usernameEditText.getText().toString();
                String password= passwordEditText.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username and password are required.", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                    User user = dbHelper.getUser(username, password);

                    if (user != null) {
                        // kullanıcı adı ve şifre doğru
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("username", user.getUsername());
                        intent.putExtra("password", user.getPassword());
                        startActivity(intent);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("user_id", userId);
                        editor.apply();
                        finish();
                    } else {
                        // kullanıcı adı veya şifre yanlış
                        Toast.makeText(getApplicationContext(), "Username and password are wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    //begin gestures
    @Override
    public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
        textLast.setText("www.elegant.com");
        return true;
    }

    @Override
    public boolean onDoubleTap(@NonNull MotionEvent e) {
        textLast.setText("Ω Make your style chic Ω");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(@NonNull MotionEvent e) {
        textLast.setText("~Elegant~");
        return true;
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        textLast.setText("~Contact us~");
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        textLast.setText("~Everywhere~");
    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        textLast.setText("~Join Us~");
        return true;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        textLast.setText("~Discover us~");
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        textLast.setText("~Sign in to explore~");
    }

    @Override
    public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        textLast.setText("~~~~~");
        return true;
    }
    //end gestures
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}