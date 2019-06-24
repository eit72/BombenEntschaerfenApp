package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

public interface BeaconHandlerCallback {
    void OnConnected();
    void OnStatusChanged(int id);
}
