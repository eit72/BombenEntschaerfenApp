package de.rvwbk.eit72.bombenentschaerfenapp.exeptions;

public class CustomeDatabaseExeption extends Exception {
    public CustomeDatabaseExeption(String errorMessage) {
        super(errorMessage);
    }
    public CustomeDatabaseExeption(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }
}
