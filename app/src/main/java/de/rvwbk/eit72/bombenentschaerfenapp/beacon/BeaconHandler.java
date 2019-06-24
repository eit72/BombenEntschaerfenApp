package de.rvwbk.eit72.bombenentschaerfenapp.beacon;


import android.content.Context;
import android.content.Intent;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BeaconHandler{
    private static BeaconHandler INSTANCE = new BeaconHandler();
    private static UUID uuid = UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D");

    private Context context;
    private BeaconManager beaconManager;
    private ArrayList<BeaconRegion> regions;
    private List<BeaconBean> beans;
    private BeaconHandlerCallback callback;
    private int currentIndex = -1;
    private boolean isConnected = false;

public static BeaconHandler getHandler(Context context, BeaconHandlerCallback callback){
    INSTANCE.setContext(context);
    INSTANCE.setCallback(callback);

    return INSTANCE;
}

    private BeaconHandler(){
        beans = BeaconFactory.getBeans(uuid);
        beaconManager = new BeaconManager(context);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                //regions = BeaconFactory.createRegions(uuid);

                isConnected = true;
                listenToNext();
                callback.OnConnected();
            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> beacons) {
                BeaconBean beacon = getBeacon(beaconRegion.getIdentifier());
                Intent intent = new Intent(BeaconHandler.this.context, beacon.getGameActivity());
                BeaconHandler.this.context.startActivity(intent);
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                //nothing to do
            }
        });

        beaconManager.setBackgroundScanPeriod(1000, 1000);
    }

    private void setContext(Context context){
    this.context = context;
    }

    private void setCallback(BeaconHandlerCallback callback){
    this.callback = callback;
    }

    public BeaconBean getBeacon(String identifier){
        //for (int i = 0; i < regions.size(); i++) {
        //    if (regions.get(i).getIdentifier() == identifier)
        //        return i;
        //}

        for(BeaconBean beacon: this.beans){
            if (beacon.getBeaconRegion().getIdentifier().equals(identifier) )
                return beacon;
        }

        return null;
    }

    public boolean listenToNext(){
        if(beans == null || beans.isEmpty()){//if (regions == null || regions.isEmpty()){
            return false;
        }

        if (currentIndex != -1){
            BeaconBean beacon = beans.get(currentIndex);
            SetBeaconStatus(beacon, BeaconStatus.PASSED);
            beaconManager.stopMonitoring(beacon.getBeaconRegion().getIdentifier());
        }

        currentIndex++;

        if (currentIndex > beans.size() - 1){
            return false;
        }
        BeaconBean beacon = beans.get(currentIndex);
        SetBeaconStatus(beacon, BeaconStatus.ACTIVE);
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

    private void SetBeaconStatus(BeaconBean bean, BeaconStatus status){
        bean.setBeaconStatus(status);
        callback.OnStatusChanged(bean.getId());
    }
}
