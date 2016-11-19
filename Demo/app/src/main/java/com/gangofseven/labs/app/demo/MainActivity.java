package com.gangofseven.labs.app.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private Button create;
    private EditText cardNumber;
    // Write a message to the database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        initCreateNumber();
    }

    private void initCreateNumber(){
        create = (Button) findViewById(R.id.create);
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
