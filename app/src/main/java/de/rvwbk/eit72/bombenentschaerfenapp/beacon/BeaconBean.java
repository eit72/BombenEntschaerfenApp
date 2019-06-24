package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;

import java.util.Objects;
import java.util.UUID;

public class BeaconBean implements Comparable<BeaconBean>, BeaconViewDetail {
    private UUID uuid;
    private int major;
    private int minor;
    private int id;
    private BeaconRegion beaconRegion;
    private Class<? extends Activity> gameActivity;
    private String hintText;
    private BeaconStatus status = BeaconStatus.PENDING;

    public BeaconBean(UUID uuid, int major, int minor, int id, Class<? extends Activity> gameActivity, String hintText){
        this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
        this.major = Objects.requireNonNull(major, "major must not be null");
        this.minor = Objects.requireNonNull(minor, "minor must not be null");
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.gameActivity = Objects.requireNonNull(gameActivity, "gameActivity must not be null");
        this.hintText = Objects.requireNonNull(hintText, "hintText must not be null");
        this.beaconRegion = Objects.requireNonNull(createRegion(uuid, major, minor), "beaconRegion must not be null");
    }


    public int getId() {
        return id;
    }

    public Class<? extends Activity> getGameActivity() {
        return gameActivity;
    }

    public String getHintText() {
        return hintText;
    }

    public BeaconRegion getBeaconRegion() { return beaconRegion; }

    @Override
    public int compareTo(@NonNull BeaconBean o) {
        return this.id - o.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uuid, this.major, this.minor, this.id, this.gameActivity, this.hintText);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        BeaconBean other = (BeaconBean) obj;

        return (other.uuid.equals(this.uuid) && other.major == this.major && other.minor == this.minor && other.id == this.id && this.gameActivity.equals(other.gameActivity) && this.hintText.equals(other.hintText));
    }

    @Override
    public String toString() {
        return String.format("BeaconBean (id: %d, minor: %d, major: %d, HasRegion: %b)", getId(), minor, major, beaconRegion.getIdentifier(), beaconRegion != null);
    }

    private static BeaconRegion createRegion(UUID uuid, int major, int minor){
        BeaconRegion region = new BeaconRegion(String.valueOf(minor) + "_" + String.valueOf(major), uuid, major, minor);

        return region;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public BeaconStatus getStatus() {
        return this.status;
    }

    public void setBeaconStatus(BeaconStatus status){
       this.status =  status;
    }
}
