package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

/**
 * View details of a beacon.
 */
public interface BeaconViewDetail {

    /**
     * Returns the title of the beacon.
     * @return The title of the beacon.
     */
    public String getTitle();

    /**
     * Returns a hint to find the next position of a beacon.
     * @return A hint to find the next position of a beacon.
     */
    public String getHintText();

    /**
     * Returns the current status of the beacon.
     * @return The current status of the beacon.
     */
    public BeaconStatus getStatus();

    /**
     * Returns the id of the beacon.
     * @return The id of the beacon.
     */
    public int getId();
}
