package de.rvwbk.eit72.bombenentschaerfenapp.overview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.rvwbk.eit72.bombenentschaerfenapp.R;
import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconHandler;
import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconHandlerCallback;
import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconViewDetail;

public class OverviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<BeaconViewDetail> beaconViewDetails = new ArrayList<>();
    private BeaconHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);


        handler = new BeaconHandler(getApplicationContext(), getUUID(), new BeaconHandlerCallback() {
            @Override
            public void OnEnter(int index) {

            }

            @Override
            public void OnExit(int index) {

            }

            @Override
            public void OnConnected() {
                beaconViewDetails.addAll(handler.getAllBeacons());
                mAdapter.notifyDataSetChanged();
            }
        });

        this.beaconViewDetails.addAll(handler.getAllBeacons());

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Hier anstatt des Arrays mit Strings die Objekte übergeben
        // Liste der Beacons an den Adapter übergeben
        mAdapter = new TaskAdapter(beaconViewDetails);

        recyclerView.setAdapter(mAdapter);

    }

    private UUID getUUID(){
        String str = getString(R.string.beacon_UUID);

        return UUID.fromString(str);
    }
}
