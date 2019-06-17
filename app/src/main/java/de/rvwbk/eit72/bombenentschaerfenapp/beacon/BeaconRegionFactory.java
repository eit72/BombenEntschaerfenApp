package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BeaconRegionFactory {
    private static ArrayList<BeaconBean> availableBeacons = getAvailableBeacons();
    private static UUID currentUUID;

    private static ArrayList<BeaconBean> getAvailableBeacons(){
        ArrayList<BeaconBean> beans = new ArrayList<>();
        for (int i = 0; i < getAvailableBeaconMinMaj().size(); i++) {

        }
        return null;
    }

    private static Map<Integer, Integer> getAvailableBeaconMinMaj(){
        Map<Integer, Integer> result = new HashMap<>();
        result.put(6389, 16546);
        result.put(25165, 33721);
        result.put(16049, 43534);
        result.put(9015, 62082);
        result.put(22027,53129);
        result.put(14943,36132);
        //result.put(0,0);
        result.put(47140, 49207);
        result.put(5975, 62040);

        return result;
    }

    public static ArrayList<BeaconRegion> createRegions(UUID beaconUUID){
        currentUUID = beaconUUID;
        ArrayList<BeaconRegion> regions = new ArrayList<>();
/*        for (Map.Entry<Integer, Integer> keyValue : availableBeacons.entrySet()) {
            BeaconRegion newRegion = getRegion(beaconUUID, keyValue.getValue(), keyValue.getKey());

            regions.add(newRegion);
        }*/

        return regions;
    }

    public static BeaconRegion getRegion(UUID uuid, int major, int minor){
        BeaconRegion region = new BeaconRegion(String.valueOf(minor) + "_" + String.valueOf(major), uuid, major, minor);

        return region;
    }
}
