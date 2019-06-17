package de.rvwbk.eit72.bombenentschaerfenapp.database;

import org.json.JSONObject;

import java.net.URL;
import java.util.Scanner;

import de.rvwbk.eit72.bombenentschaerfenapp.exeptions.CustomeDatabaseExeption;
import de.rvwbk.eit72.bombenentschaerfenapp.models.User;

public class Database {

    public static User getUser(String username) throws CustomeDatabaseExeption {
        try {
            String str = getJsonString("users");
            // build a JSON object
            JSONObject obj = new JSONObject(str);
            if (!obj.getString("status").equals("OK")){
                throw new CustomeDatabaseExeption("DB: Kein 200er Response");
            }
        }catch (Exception e){
            throw new CustomeDatabaseExeption("Fehler beim hohlen der DB-Informationen",e);
        }
        throw new UnsupportedOperationException();
    }

    public static User setUser(User user) {
        throw new UnsupportedOperationException();
    }

    public static User delUser(User user) {
        throw new UnsupportedOperationException();
    }

    public static User updateUser(User user) {
        throw new UnsupportedOperationException();
    }

    private static String getJsonString(String relativeUrl) throws Exception {
        String baseUrl = "https://firestore.googleapis.com/v1/projects/schulprojekt-14e30/databases/(default)/documents/";
        baseUrl += relativeUrl;
        URL url = new URL(baseUrl);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String returnValue = "";
        while (scan.hasNext()) {
          returnValue += scan.nextLine();
        }
        scan.close();

        return returnValue;
    }

    private static void geocoding() throws Exception {
        //String addr = "";
        // build a URL
        String baseUrl = "https://firestore.googleapis.com/v1/projects/schulprojekt-14e30/databases/(default)/documents/users";
        //baseUrl += URLEncoder.encode(addr, "UTF-8");
        URL url = new URL(baseUrl);

        // read from the URL
        Scanner scan = new Scanner(url.openStream());
        String str = "";
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);
        if (!obj.getString("status").equals("OK"))
            return;

        // get the first result
        JSONObject res = obj.getJSONArray("results").getJSONObject(0);
        System.out.println(res.getString("formatted_address"));
        JSONObject loc =
                res.getJSONObject("geometry").getJSONObject("location");
        System.out.println("lat: " + loc.getDouble("lat") +
                ", lng: " + loc.getDouble("lng"));
    }
}
