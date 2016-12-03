package com.gangofseven.labs.app.demo.UI.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gangofseven.labs.app.demo.DatabaseUtil;
import com.gangofseven.labs.app.demo.R;
import com.gangofseven.labs.app.demo.models.JuanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class JuanActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juan);

        mDatabase = DatabaseUtil.getDatabase();

        users = mDatabase.getReference().child("users");

        writeNewUser("Juan", "conchudo@juannnn.com");
        writeNewUser("Maria", "conchudo@juannnn.com");
        writeNewUser("Pedro", "conchudo@juannnn.com");
        writeNewUser("Concha", "conchudo@juannnn.com");
        writeNewUser("Lora", "conchudo@juannnn.com");

        readData();

        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);

    }

    private void readData() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);
                // ...

                JuanModel juanModel = dataSnapshot.getValue(JuanModel.class);
                Log.i("JuanEmailLog", juanModel.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        users.child("fc9eb46d-43cf-40ce-8dbf-1f95580029c1").addValueEventListener(valueEventListener);
    }

    private void writeNewUser(String username, String email) {
        JuanModel juanModel = new JuanModel();
        juanModel.setNombre(username);
        juanModel.setEmail(email);

        String uniqueId = UUID.randomUUID().toString();

        users.child(uniqueId).setValue(juanModel);
    }

}
