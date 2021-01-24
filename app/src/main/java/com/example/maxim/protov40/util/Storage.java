package com.example.maxim.protov40.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Storage {
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static Storage INSTANCE = new Storage();

    private Storage() {
    }

    public void createFolder(Folder folder) {
        String key = Session.getINSTANCE().getUser().getId();
        String folderKey = database.child("users").child(key).child("folders").push().getKey();
        database.child("users").child(key).child("folders").child(folderKey).setValue(folder);
        folder.setId(folderKey);
        folder.getTodos().add(new ToDo(" ", "", ""));
        Session.getINSTANCE().getUser().getFolders().add(folder);
    }

    public void createTodo(ToDo toDo, int position) {
        String userKey = Session.getINSTANCE().getUser().getId();
        String folderKey = Session.getINSTANCE().getUser().getFolders().get(position).getId();
        String key = database.child("users").child(userKey).child("folders").child(folderKey).push().getKey();
        database.child("users").child(userKey).child("folders").child(folderKey).child("todos").child(key).setValue(toDo);
        toDo.setId(key);
        Session.getINSTANCE().getUser().getFolders().get(position).getTodos().add(toDo);
    }

    public static Storage getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(Storage INSTANCE) {
        Storage.INSTANCE = INSTANCE;
    }
}
