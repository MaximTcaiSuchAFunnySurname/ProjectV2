package com.example.maxim.protov40.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.fragments.dialog.CreateFolderDialogFragment;
import com.example.maxim.protov40.util.Folder;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.Storage;
import com.example.maxim.protov40.util.ToDo;
import com.example.maxim.protov40.util.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.baoyz.swipemenulistview.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoldersFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, CreateFolderDialogFragment.ICreateDialog, AdapterView.OnItemLongClickListener {
    private Button create;
    private DatabaseReference database;
    private List<User> users;
    private List<String> listOfFolders;
    private ArrayAdapter<String> adapter;
    private SwipeMenuListView listView;
    private Button back;

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
        create = (Button) view.findViewById(R.id.create_button);
        back = (Button) view.findViewById(R.id.back_button);
        try {
            for (Folder elem : Session.getINSTANCE().getUser().getFolders()
                    ) {
                if (!elem.getName().equals(""))
                    listOfFolders.add(elem.getName());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfFolders);
        listView = (SwipeMenuListView) view.findViewById(R.id.list_folders);
        listView.setAdapter(adapter);
        create.setOnClickListener(this);
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem open = new SwipeMenuItem(getActivity());
                SwipeMenuItem delete = new SwipeMenuItem(getActivity());
                open.setBackground(new ColorDrawable(Color.GREEN));
                open.setWidth(220);
                open.setIcon(R.drawable.open);
                menu.addMenuItem(open);
                delete.setBackground(new ColorDrawable(Color.RED));
                delete.setWidth(220);
                delete.setIcon(R.drawable.delete);
                menu.addMenuItem(delete);
            }
        };
        listView.setMenuCreator(swipeMenuCreator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Bundle bundle = new Bundle();
                        bundle.putInt("folderIndex", position);
                        TodoListFragment fragment = new TodoListFragment();
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, fragment);
                        transaction.commit();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Deleting " + Session.getINSTANCE()
                                .getUser().getFolders().get(position), Toast.LENGTH_LONG).show();
                        listOfFolders.remove(position);
                        adapter.notifyDataSetChanged();
                        String userKey = Session.getINSTANCE().getUser().getId();
                        String folderKey = Session.getINSTANCE().getUser().getFolders().get(position).getId();
                        Session.getINSTANCE().getUser().getFolders().remove(position);
                        database.child("users").child(userKey).child("folders")
                                .child(folderKey).removeValue();
                        break;
                }
                return true;
            }
        });
        back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_button:
                CreateFolderDialogFragment dialogFragment = new CreateFolderDialogFragment();
                dialogFragment.setmListener(this);
                dialogFragment.show(getFragmentManager(), "0");
                break;
            case R.id.back_button:
                LogInFragment fragment = new LogInFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("folderIndex", position);
        TodoListFragment fragment = new TodoListFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText edit = (EditText) dialog.getDialog().findViewById(R.id.foldername_edit);
        if (!edit.getText().toString().equals("")) {
            Storage.getINSTANCE().createFolder(new Folder(edit.getText().toString(), null));
            listOfFolders.add(edit.getText().toString());
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity(), "Empty folder name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Deleting " + Session.getINSTANCE()
                .getUser().getFolders().get(position), Toast.LENGTH_LONG).show();
        listOfFolders.remove(position);
        adapter.notifyDataSetChanged();
        String userKey = Session.getINSTANCE().getUser().getId();
        String folderKey = Session.getINSTANCE().getUser().getFolders().get(position).getId();
        Session.getINSTANCE().getUser().getFolders().remove(position);
        database.child("users").child(userKey).child("folders")
                .child(folderKey).removeValue();
        return true;
    }
}
