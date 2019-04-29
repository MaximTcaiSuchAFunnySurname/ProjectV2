package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.util.Folder;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FoldersFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button create;
    private DatabaseReference database;
    private List<User> users;
    private List<String> listOfFolders;

    public FoldersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //TODO: работа с документом(структура данных) + папки & базы данных(по юзеру)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.folders_layout, container, false);
        //TODO: serializable метод hashmapToFolder
        for (Folder elem : Session.getINSTANCE().getUser().getFolders()
                ) {
            if (!elem.getName().equals(""))
                listOfFolders.add(elem.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfFolders.toArray());
        ListView listView = (ListView) view.findViewById(R.id.list_folders);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
