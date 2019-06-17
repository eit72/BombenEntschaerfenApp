package de.rvwbk.eit72.bombenentschaerfenapp.beacon;


import android.content.Context;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeaconHandler {
    private BeaconManager beaconManager;
    private ArrayList<BeaconRegion> regions;
    private BeaconHandlerCallback callback;
    private int currentIndex = -1;
    private boolean isConnected = false;

    public BeaconHandler(Context context, final UUID uuid, final BeaconHandlerCallback newCallback){
        callback = newCallback;
        beaconManager = new BeaconManager(context);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                regions = BeaconRegionFactory.createRegions(uuid);
                isConnected = true;
                listenToNext();
                newCallback.OnConnected();
            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> beacons) {
                callback.OnEnter(getRegionIndex(beaconRegion.getIdentifier()));
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                callback.OnExit(getRegionIndex(beaconRegion.getIdentifier()));
            }
        });

        beaconManager.setBackgroundScanPeriod(5000, 10000);
    }

    public int getRegionIndex(String identifier){
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).getIdentifier() == identifier)
                return i;
        }

        return -1;
    }

    public boolean listenToNext(){
        if (regions == null || regions.isEmpty()){
            return false;
        }

        if (currentIndex != -1){
            beaconManager.stopMonitoring(regions.get(currentIndex).getIdentifier());
        }

        currentIndex++;

        if (currentIndex > regions.size() - 1){
            return false;
        }

        beaconManager.startMonitoring(regions.get(currentIndex));

        return true;
    }

    public boolean isConnected(){
        return isConnected;
    }

    // Debug only
    public void listenToAll(){
        for (BeaconRegion region : regions){
            beaconManager.startMonitoring(region);
        }
    }

    public void onDestroy(){
        beaconManager.disconnect();
    }
}
