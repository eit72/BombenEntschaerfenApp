# BombenEntschaerfenApp

# Regeln für Commit-Messages

* Trennen Sie die Betreffzeile vom Inhalt mit einer Leerzeile.
* Beschränken Sie die Betreffzeile auf 50 Zeichen.
* Beenden Sie die Betreffzeile nicht mit einem Punkt.
* Beschränken Sie den Inhalt auf 72 Zeichen.
* Schreiben sie die Commit-Message auf Deutsch.
* Verwenden Sie den Inhalt, um zu erklären, was und warum und wie.


# Datenbankzugriff

Als Datenbank nutzen wir eine Cloud Firestore Datenbank von Google, welche eine NoSql-Cloud-Datenbank ist. 

Innerhalb der Datenbank gibt es folgende Elemente:
1. Die Sammlung:
Die Sammlung stellt im Prinzip eine Tabelle dar welche alle dazugeöhrigen Dokumente enthält. Die Sammlung würde also z.B. "users" heißen.

2. Das Dokument:
Das Dokument stellt den eigentlichen Datensatz dar. Er benötigt einen eindeutigen Identifier. Beim Beispiel "users" würde sich also die E-Mail oder der Username anbieten um als Identifier der einzelnen Nutzer

3. Die Felder:
Die einzelnen Felder des Dokumentes stellen einfach "Key : Value"-Paare dar. Außerdem können diese wieder weitere Sammlungen enthalten. Hierbei ist zu beachten das sich die einzelen Dokumente innerhalb einer Sammlung in der Art und der Anzahl ihrer Felder unterscheiden können. Es gibt also keine Vorlagen wie die Dokumente aufgebaut werden. Darauf muss man beim Speichern, Bearbeiten und verarbeiten der Daten in der Datenbank achten.

Mithilfe der Folgenden Code-Zeile kann auf die Datenbank zugegriffen werden.
```Java
FirebaseFirestore db = FirebaseFirestore.getInstance();
```

Mithilfe der aufgebauten Verbindung können dann komplette Samlungen oder einzelne Dokumente abgerufen werden:
```Java
db.collection("Sammlungsname")
        // Diese Zeile ist Optional und dient dazu ein bestimmtes 
        // Dokument in der zuvor angegebenen Sammlung zu finden
        .document("DokumentenID") 
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                // Logic
            }
        });
```

Außerdem kann man die Daten ebenfalls erstellen bzw. updaten oder löschen:
1. Erstellen:
```Java
// Create a new user with a first and last name
Map<String, Object> user = new HashMap<>();
user.put("first", "Adam");
user.put("last", "Lovelace");
user.put("born", 1815);

db.collection("users")
  .document("Coller User")
  .set(user)
  .addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void aVoid) {
        // Logic
      }
  })
  .addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull Exception e) {
        // Logic
      }
  });
}
```

2. Update:
```Java
// Update one field, creating the document if it does not already exist.
Map<String, Object> data = new HashMap<>();
data.put("is_super", true);

db.collection("users").document("user")
  .set(data, SetOptions.merge());
```
ODER
```Java
DocumentReference user = db.collection("users").document("user");

user.update("key", "value")
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Logic
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Logic
            }
        });
```

3. Löschen
```Java 
db.collection("users").document("user")
  .delete()
  .addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void aVoid) {
        // Logic
    }
})
  .addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        // Logic
    }
});

```

Die Daten welche gesetzt werden können, können auch als eingene Klasse abgebildet werden.
Beispiel: 

```Java
public class City {


    private String name;
    private String state;
    private String country;
    private boolean capital;
    private long population;
    private List<String> regions;

    public City() {}

    public City(String name, String state, String country, boolean capital, long population, List<String> regions) {
        // ...
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public boolean isCapital() {
        return capital;
    }

    public long getPopulation() {
        return population;
    }

    public List<String> getRegions() {
        return regions;
    }

}


City city = new City("Los Angeles", "CA", "USA", false, 5000000L, Arrays.asList("west_coast", "sorcal"));
db.collection("cities").document("LA").set(city);
```
