package de.rvwbk.eit72.bombenentschaerfenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.UUID;

import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconHandlerCallback;
import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconHandler;
import de.rvwbk.eit72.bombenentschaerfenapp.walkthrough.WalkthroughActivity;

public class MainActivity extends AppCompatActivity {
    private BeaconHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        handler = new BeaconHandler(getApplicationContext(), getUUID(), new BeaconHandlerCallback() {
            @Override
            public void OnEnter(int index) {
                Toast.makeText(getApplicationContext(), "Entered: " + String.valueOf(index), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnExit(int index) {
                Toast.makeText(getApplicationContext(), "Exited: " + String.valueOf(index), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnConnected(){
                handler.listenToAll();
            }
        });

        Intent intent = new Intent(this, WalkthroughActivity.class);
        this.startActivity(intent);
    }

    private UUID getUUID(){
        String str = getString(R.string.beacon_UUID);

        return UUID.fromString(str);
    }

    @Override
    protected void onDestroy() {
        handler.onDestroy();
        super.onDestroy();
    }
}
