package de.rvwbk.eit72.bombenentschaerfenapp.Models;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class User {

    public static User getUser(String id) throws Exception {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Task<DocumentSnapshot> task = db.collection("users")
                // Diese Zeile ist Optional und dient dazu ein bestimmtes
                // Dokument in der zuvor angegebenen Sammlung zu finden
                .document(id)
                .get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                    }
//                })
                ;
        while(!task.isComplete()){
            Thread.sleep(100);
        }
        try {
            return User.toUser(task.getResult());
        } catch (Exception e) {
            Log.e("fail", e.getMessage());
            throw e;
        }

    }

    public static User toUser(DocumentSnapshot userObject) {
        User user = new User();
        user.setBorn(userObject.getLong("born"));
        user.setFirstname(userObject.getString("first"));
        user.setLastname(userObject.getString("last"));

        return user;
    }

    private Long born;
    private String lastname;
    private String firstname;

    public Long getBorn() {
        return born;
    }

    public void setBorn(Long born) {
        this.born = born;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
