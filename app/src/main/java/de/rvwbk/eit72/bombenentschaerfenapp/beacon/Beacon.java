package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.Objects;
import java.util.UUID;

public class Beacon implements Comparable<Beacon>{
    private UUID uuid;
    private int major;
    private int minor;
    private int id;
    private Activity gameActivity;
    private String hintText;

    public Beacon (UUID uuid, int major, int minor, int id, Activity gameActivity, String hintText){
        this.uuid = Objects.requireNonNull(uuid, "uuid must not be null");
        this.major = Objects.requireNonNull(major, "major must not be null");
        this.minor = Objects.requireNonNull(minor, "minor must not be null");
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.gameActivity = Objects.requireNonNull(gameActivity, "gameActivity must not be null");
        this.hintText = Objects.requireNonNull(hintText, "hintText must not be null");
    }


    public int getId() {
        return id;
    }

    public Activity getGameActivity() {
        return gameActivity;
    }

    public String getHintText() {
        return hintText;
    }

    @Override
    public int compareTo(@NonNull Beacon o) {
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

        Beacon other = (Beacon) obj;

        return (other.uuid.equals(this.uuid) && other.major == this.major && other.minor == this.minor && other.id == this.id && this.gameActivity.equals(other.gameActivity) && this.hintText.equals(other.hintText));
    }
}
