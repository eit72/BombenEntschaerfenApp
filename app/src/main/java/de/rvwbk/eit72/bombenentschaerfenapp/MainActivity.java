package de.rvwbk.eit72.bombenentschaerfenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.rvwbk.eit72.bombenentschaerfenapp.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HashMap<String, Object>> documents = new ArrayList<>();
        HashMap<String, Object> quest1 = new HashMap<>();
        HashMap<String, Object> quest2 = new HashMap<>();

        quest1.put("title", "Die erste Quest");
        quest1.put("description", "Die erste Quest Beschreibung");
        quest2.put("title", "Die zweite Quest");
        quest2.put("description", "Die zweite Quest Beschreibung");

        documents.add(quest1);
        documents.add(quest2);

        DatabaseHelper.insertCollection("dummyQuests", documents);
//        System.out.println("HEY: " + DatabaseHelper.getCollectionDocuments("users").toString());
    }
}
