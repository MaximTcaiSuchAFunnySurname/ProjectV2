package com.example.maxim.protov40.fragments;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.fragments.dialog.CreateTodoDialogFragment;
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

public class TodosFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, CreateTodoDialogFragment.CreateTodoDialogListener {
    private Button create;
    private DatabaseReference database;
    private List<User> users;
    private List<String> listOfTodo;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private int position;
    private Button back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todos_layout, container, false);
        create = (Button) view.findViewById(R.id.create_button);
        back = (Button) view.findViewById(R.id.btn_back);
        position = getArguments().getInt("pos");
        listOfTodo = new ArrayList<String>();
        database = FirebaseDatabase.getInstance().getReference();
        for (ToDo elem : Session.getINSTANCE().getUser().getFolders().get(position).getTodos()
        ) {
            if (!elem.getName().equals(""))
                listOfTodo.add(elem.getName());
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listOfTodo);
        listView = (ListView) view.findViewById(R.id.list_todos);
        listView.setAdapter(adapter);
        create.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                FoldersFragment foldersFragment = new FoldersFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, foldersFragment);
                fragmentTransaction.commit();
                break;
            case R.id.create_button:
                CreateTodoDialogFragment ctdFragment = new CreateTodoDialogFragment();
                ctdFragment.setTodosFragment(this);
                ctdFragment.show(getFragmentManager(), "ctdFragment");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TodoFragment todoFragment = new TodoFragment();
        ToDo toDo = Session.getINSTANCE().getUser().getFolders().get(this.position).getTodos().get(position);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("name", toDo.getName());
        bundle.putString("disc", toDo.getDisc());
        bundle.putString("time", toDo.getTime());
        bundle.putInt("pos", this.position);
        todoFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame, todoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText editName = (EditText) dialog.getDialog().findViewById(R.id.todoname);
        EditText editDisc = (EditText) dialog.getDialog().findViewById(R.id.tododisc);
        EditText editTime = (EditText) dialog.getDialog().findViewById(R.id.todotime);
        ToDo toDo = new ToDo(editName.getText().toString(),
                editDisc.getText().toString(),
                editTime.getText().toString());
        Storage.getINSTANCE().createTodo(toDo, position);
        listOfTodo.add(toDo.getName());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
