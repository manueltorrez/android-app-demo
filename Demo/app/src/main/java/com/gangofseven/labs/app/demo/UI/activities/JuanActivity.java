package com.gangofseven.labs.app.demo.UI.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chocoyo.labs.adapters.progress.AdapterProgress;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

    private RecyclerView mRecyclerView;

    private FirebaseDatabase mDatabase;
    private DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juan);

        mDatabase = DatabaseUtil.getDatabase();

        users = mDatabase.getReference().child("users");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new AdapterProgress());

        /*writeNewUser("Juan", "conchudo@juannnn.com");
        writeNewUser("Maria", "conchudo@juannnn.com");
        writeNewUser("Pedro", "conchudo@juannnn.com");
        writeNewUser("Concha", "conchudo@juannnn.com");
        writeNewUser("Lora", "conchudo@juannnn.com");*/

        //readData();


    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<JuanModel, CarViewHolder> adapter = new FirebaseRecyclerAdapter<JuanModel, CarViewHolder>(
                JuanModel.class,
                R.layout.item_juan,
                CarViewHolder.class,
                users) {
            @Override
            protected void populateViewHolder(CarViewHolder viewHolder, final JuanModel juanModel, final int position) {

                // set key to send to edit activity
                final String key = this.getRef(position).getKey();

                viewHolder.username.setText(juanModel.getNombre());
                viewHolder.email.setText(juanModel.getEmail());

/*

                // open activity to edit
                viewHolder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), AddCarActivity.class);
                        intent.putExtra(AddCarActivity.EXTRA_KEY, key);
                        startActivity(intent);
                    }
                });
                */
            }
        };

        mRecyclerView.setAdapter(adapter);
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView email;
        public CarViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            email = (TextView) view.findViewById(R.id.email);
        }
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

        users.child("0a2908af-d791-45cb-aa23-a07aef4b5905").addValueEventListener(valueEventListener);
    }

    private void writeNewUser(String username, String email) {
        JuanModel juanModel = new JuanModel();
        juanModel.setNombre(username);
        juanModel.setEmail(email);

        String uniqueId = UUID.randomUUID().toString();

        users.push().setValue(juanModel);
    }

}
