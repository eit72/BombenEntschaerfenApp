package de.rvwbk.eit72.bombenentschaerfenapp.overview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.rvwbk.eit72.bombenentschaerfenapp.R;
import de.rvwbk.eit72.bombenentschaerfenapp.beacon.BeaconViewDetail;

public class OverviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<BeaconViewDetail> beaconViewDetails = new ArrayList<BeaconViewDetail>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Hier anstatt des Arrays mit Strings die Objekte übergeben
        // Liste der Beacons an den Adapter übergeben
        mAdapter = new TaskAdapter(beaconViewDetails);

        recyclerView.setAdapter(mAdapter);

    }
}
