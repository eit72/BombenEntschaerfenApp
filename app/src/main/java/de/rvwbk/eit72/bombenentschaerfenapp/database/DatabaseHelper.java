package de.rvwbk.eit72.bombenentschaerfenapp.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DatabaseHelper {
    private static FirebaseFirestore DB = FirebaseFirestore.getInstance();

    public static HashMap<String, Object> getCollectionDocuments(String collectionName){
        final HashMap<String, Object> documents = new HashMap<>();
        documents.put("hey", "ho");

        Task task = DB.collection(collectionName).get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
//DB.collection(collectionName)
//    .add(user)
//        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//    @Override
//    public void onSuccess(DocumentReference documentReference) {
//      Log.d("cTAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//    }
//  })
//    .addOnFailureListener(new OnFailureListener() {
//    @Override
//    public void onFailure(@NonNull Exception e) {
//      Log.w("cTAG", "Error adding document", e);
//    }
//  });
    public static void insertCollection(String collectionName, List<HashMap<String, Object>> documents){
      Iterator it = documents.iterator();
      while(it.hasNext()){
        DB.collection(collectionName).add(it.next());
      }
    }
}
