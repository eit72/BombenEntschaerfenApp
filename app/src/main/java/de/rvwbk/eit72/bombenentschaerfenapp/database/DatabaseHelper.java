package de.rvwbk.eit72.bombenentschaerfenapp.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class DatabaseHelper {
    private static FirebaseFirestore DB = FirebaseFirestore.getInstance();

    public static HashMap<String, Object> getCollectionDocuments(String collectionName){
        final HashMap<String, Object> documents = new HashMap<>();
        documents.put("hey", "ho");

        DB.collection(collectionName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        documents.put(document.getId(), document.getData());
                        Log.d("tagg", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w("tagg", "Error getting documents.", task.getException());
                }
            }
        });

        return documents;
    }
}
