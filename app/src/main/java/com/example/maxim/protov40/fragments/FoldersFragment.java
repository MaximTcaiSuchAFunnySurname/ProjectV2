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
import com.example.maxim.protov40.util.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoldersFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button create;
    private DatabaseReference database;
    private List<User> users;
    private List<String> listOfNotes;

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
//        database = FirebaseDatabase.getInstance().getReference();
//        users = new ArrayList<>();
//        listOfNotes = new ArrayList<>();
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.folders
                , android.R.layout.simple_list_item_1);
        ListView listView = (ListView) view.findViewById(R.id.list_folders);
//        database.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot elem : dataSnapshot.getChildren()
//                        ) {
//                    HashMap map = (HashMap) elem.getValue();
//                    HashMap map2 = (HashMap) map.get(map.keySet().toArray()[0]);
//                    User user = new User(map2.get("login").toString(), map2.get("password").toString(), new ArrayList<String>());
//                    users.add(user);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        for (User user: users
//             ) {
//            if (user.getLogin().equals(getArguments().getString("login"))){
//                listOfNotes = user.getList();
//                break;
//            }
//        }
//        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, listOfNotes);
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
