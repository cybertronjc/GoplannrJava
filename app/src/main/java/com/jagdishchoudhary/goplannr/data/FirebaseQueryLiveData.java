package com.jagdishchoudhary.goplannr.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {
    private static final String LOG_TAG = "FirebaseQueryLiveData";
    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();


    public FirebaseQueryLiveData(DatabaseReference ref) {
        this.query = ref;
    }

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        query.addListenerForSingleValueEvent(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        query.removeEventListener(listener);

    }

    private class MyValueEventListener implements ValueEventListener{
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
