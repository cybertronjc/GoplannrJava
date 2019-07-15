package com.jagdishchoudhary.goplannr.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jagdishchoudhary.goplannr.data.FirebaseQueryLiveData;

public class PlanViewModel extends ViewModel {
    private static final DatabaseReference PLAN_REF =
            FirebaseDatabase.getInstance().getReference().child("plans");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(PLAN_REF);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }

}
