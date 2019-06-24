package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

import android.app.Activity;

import java.util.Objects;

import de.rvwbk.eit72.bombenentschaerfenapp.MainActivity;

public enum BeaconMapping {
    // TODO Activities und Hinweis-Texte müssen ersetzt werden.
    FIRST_GAME(47140, 49207, MainActivity.class, "Das ist der Hinweis-Text für das erste Beacon"),
    SECOND_GAME(25165, 33721, MainActivity.class, "Das ist der Hinweis-Text für das zweite Beacon"),
    THIRD_GAME(22027, 53129, MainActivity.class, "Das ist der Hinweis-Text für das dritte Beacon");


    private final int major;
    private final int minor;
    private final Class<? extends Activity> gameActivity;
    private final String hintText;

    BeaconMapping(int major, int minor, Class<? extends Activity> gameActivity, String hintText){
        this.major = Objects.requireNonNull(major, "major must not be null");
        this.minor=  Objects.requireNonNull(minor, "minor must not be null");
        this.gameActivity = Objects.requireNonNull(gameActivity, "gameActivity must not be null");
        this.hintText = Objects.requireNonNull(hintText, "hintText must not be null");
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public Class<? extends Activity> getGameActivity() {
        return gameActivity;
    }

    public String getHintText(){
        return this.hintText;
    }
}
