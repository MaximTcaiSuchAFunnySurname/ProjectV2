package com.example.maxim.protov40.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.fragments.dialog.CreateFolderDialogFragments;
import com.example.maxim.protov40.util.Folder;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.Storage;
import com.example.maxim.protov40.util.ToDo;
import com.example.maxim.protov40.util.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoldersFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, CreateFolderDialogFragments.CreateFolderDialogListener {
    private Button create;
    private DatabaseReference database;
    private List<User> users;
    private List<String> listOfFolders;
    private ArrayAdapter<String> adapter;
    private ListView listView;

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
        create = (Button) view.findViewById(R.id.create_button);
        listOfFolders = new ArrayList<String>();
        database = FirebaseDatabase.getInstance().getReference();
        for (Folder elem : Session.getINSTANCE().getUser().getFolders()
        ) {
            if (!elem.getName().equals(""))
                listOfFolders.add(elem.getName());
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfFolders);
        listView = (ListView) view.findViewById(R.id.list_folders);
        listView.setAdapter(adapter);
        create.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_button:
                CreateFolderDialogFragments cfdFragment = new CreateFolderDialogFragments();
                cfdFragment.setFoldersFragment(this);
                cfdFragment.show(getFragmentManager(), "cfdFragment");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TodosFragment todosFragment = new TodosFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        todosFragment.setArguments(bundle);
        transaction.replace(R.id.frame, todosFragment);
        transaction.commit();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        ToDo toDo = new ToDo("","","");
        EditText editText = (EditText) dialog.getDialog().findViewById(R.id.foldername);
        Folder folder = new Folder(editText.getText().toString(), Collections.singletonList(toDo));
        Storage.getINSTANCE().createFolder(folder);
        listOfFolders.add(folder.getName());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
