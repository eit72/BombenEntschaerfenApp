package de.rvwbk.eit72.bombenentschaerfenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.rvwbk.eit72.bombenentschaerfenapp.database.DatabaseHelper;
import de.rvwbk.eit72.bombenentschaerfenapp.login.Login;


public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      button = (Button) findViewById(R.id.button);

      button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          Intent intent = new Intent(MainActivity.this, Login.class);
          startActivity(intent);
        }
      });


    }
}
