package de.rvwbk.eit72.bombenentschaerfenapp.login;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;


import de.rvwbk.eit72.bombenentschaerfenapp.MainActivity;
import de.rvwbk.eit72.bombenentschaerfenapp.R;
import de.rvwbk.eit72.bombenentschaerfenapp.database.DatabaseHelper;


public class Login extends AppCompatActivity {
  EditText tLogin;
  Button btnLogin ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    tLogin = (EditText) findViewById(R.id.txtfldLogin);
    btnLogin = (Button) findViewById(R.id.btnLogin);

    Intent intent = getIntent();

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        HashMap<String, Object> usermap = new HashMap<>();

        if (!tLogin.getText().toString().isEmpty())
        {
          usermap.put("Name", tLogin.getText().toString());

          DatabaseHelper.insertNewUser(usermap);
          Toast.makeText(Login.this,"Login erfolgreich",Toast.LENGTH_LONG).show();
        }
      }

    });


  }
}
