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
    private List<BeaconBean> beans;
    private BeaconHandlerCallback callback;
    private int currentIndex = -1;
    private boolean isConnected = false;

    public BeaconHandler(Context context, final UUID uuid, final BeaconHandlerCallback newCallback){
        beans = BeaconFactory.getBeans(uuid);
        callback = newCallback;
        beaconManager = new BeaconManager(context);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                //regions = BeaconFactory.createRegions(uuid);

                isConnected = true;
                listenToNext();
                newCallback.OnConnected();
            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> beacons) {
                callback.OnEnter(getBeaconId(beaconRegion.getIdentifier()));
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                callback.OnExit(getBeaconId(beaconRegion.getIdentifier()));
            }
        });

        beaconManager.setBackgroundScanPeriod(5000, 10000);
    }

    public int getBeaconId(String identifier){
        //for (int i = 0; i < regions.size(); i++) {
        //    if (regions.get(i).getIdentifier() == identifier)
        //        return i;
        //}

        for (int i = 0; i < beans.size(); i++){
            if (beans.get(i).getBeaconRegion().getIdentifier().equals(identifier) )
                return beans.get(i).getId();
        }

        return -1;
    }

    public boolean listenToNext(){
        if(beans == null || beans.isEmpty()){//if (regions == null || regions.isEmpty()){
            return false;
        }

        if (currentIndex != -1){
            BeaconBean beacon = beans.get(currentIndex);
            beacon.setBeaconStatus(BeaconStatus.PENDING);
            beaconManager.stopMonitoring(beacon.getBeaconRegion().getIdentifier());
        }

        currentIndex++;

        if (currentIndex > beans.size() - 1){
            return false;
        }
        BeaconBean beacon = beans.get(currentIndex);
        beacon.setBeaconStatus(BeaconStatus.ACTIVE);
        beaconManager.startMonitoring(beacon.getBeaconRegion());

        return true;
    }

    public boolean isConnected(){
        return isConnected;
    }

    // Debug only
    public void listenToAll(){
        for (BeaconBean bean : beans){
            beaconManager.startMonitoring(bean.getBeaconRegion());
        }
    }

    public void onDestroy(){
        beaconManager.disconnect();
    }

    public List<BeaconViewDetail> getAllBeacons(){
        List<BeaconViewDetail> beaconViewList = new ArrayList<>();
        beaconViewList.addAll(this.beans);
        return beaconViewList;
    }
}
