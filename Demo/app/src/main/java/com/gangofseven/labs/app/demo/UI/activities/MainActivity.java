package com.gangofseven.labs.app.demo.UI.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gangofseven.labs.app.demo.DatabaseUtil;
import com.gangofseven.labs.app.demo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.create) Button create;
    private EditText cardNumber;
    // Write a message to the database
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    @BindView(R.id.button2) Button bJuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = DatabaseUtil.getDatabase();
        myRef = mDatabase.getReference("message");

        //Initialize
        initCreateNumber();
        ButterKnife.bind(this);

        //Button bJuan = (Button) findViewById(R.id.button2);
        bJuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openJuan();
            }
        });



    }


    private void openJuan(){

        Intent intent = new Intent(MainActivity.this, JuanActivity.class);
        startActivity(intent);
    }

    private void initCreateNumber(){
        cardNumber = (EditText) findViewById(R.id.card_number);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cardNumber.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Loco, no has puesto tu numero", Toast.LENGTH_LONG).show();
                }
                else{
                    myRef.setValue(cardNumber.getText().toString());
                }
            }
        });



    }

}
