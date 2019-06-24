package de.rvwbk.eit72.bombenentschaerfenapp.beacon;

public interface BeaconHandlerCallback {
    void OnEnter(int index);
    void OnExit(int index);
    void OnConnected();
    void OnStatusChanged(int id);
}
