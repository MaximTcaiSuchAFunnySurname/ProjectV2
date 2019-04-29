package com.example.maxim.protov40.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Storage {
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static Storage INSTANCE = new Storage();

    private Storage() {
    }

    public void createFolder(Folder folder){
        String key = database.child("users").getKey();
        String folderKey = database.child("users").child(key).child("folders").getKey();
        database.child("users").child(key).child("folders").child(folderKey).setValue(folder);
        Session.getINSTANCE().getUser().getFolders().add(folder);
    }


    public static Storage getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(Storage INSTANCE) {
        Storage.INSTANCE = INSTANCE;
    }
}
