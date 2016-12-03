package com.gangofseven.labs.app.demo;

/**
 * Created by Administrador on 03/12/2016.
 */

import com.google.firebase.database.FirebaseDatabase;

public class DatabaseUtil {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }

        return mDatabase;
    }
}
