package com.example.maxim.protov40.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
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
import android.widget.Toast;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.fragments.dialog.CreateTodoDialogFragment;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.Storage;
import com.example.maxim.protov40.util.ToDo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment implements AdapterView.OnItemClickListener
        , AdapterView.OnItemLongClickListener, View.OnClickListener, CreateTodoDialogFragment.ICreateTodo {
    private ListView listView;
    private List<String> todoList;
    private ArrayAdapter<String> adapter;
    private Button createTodo;
    private Button back;
    private DatabaseReference database;

    public TodoListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_list_layout, container, false);
        listView = (ListView) view.findViewById(R.id.notes_list);
        createTodo = (Button) view.findViewById(R.id.create_todo);
        back = (Button) view.findViewById(R.id.back_button1);
        todoList = new ArrayList<>();
        Bundle bundle = new Bundle();
        for (ToDo elem : Session.getINSTANCE().getUser()
                .getFolders().get(bundle.getInt("folderIndex")).getTodos()
                ) {
            todoList.add(elem.getName());
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, todoList);
        database = FirebaseDatabase.getInstance().getReference();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        back.setOnClickListener(this);
        createTodo.setOnClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getArguments().putInt("todoIndex", position);
        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(getArguments());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button1:
                FoldersFragment fragment = new FoldersFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment);
                transaction.commit();
                break;
            case R.id.create_todo:
                CreateTodoDialogFragment dialogFragment = new CreateTodoDialogFragment();
                dialogFragment.setmListener(this);
                dialogFragment.show(getFragmentManager(), "1");
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText name = (EditText) dialog.getDialog().findViewById(R.id.todo_name_edit);
        EditText text = (EditText) dialog.getDialog().findViewById(R.id.todo_text_edit);
        EditText data = (EditText) dialog.getDialog().findViewById(R.id.todo_data_edit);
        if (!(name.getText().toString().equals("") && text.getText().toString().equals("") && data.getText().toString().equals(""))) {
            Storage.getINSTANCE().createTodo(new ToDo(name.getText().toString(), text.getText().toString()
                    , data.getText().toString()), getArguments().getInt("folderIndex"));
            todoList.add(name.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Deleting toDo...", Toast.LENGTH_SHORT).show();
        String userKey = Session.getINSTANCE().getUser().getId();
        String folderKey = Session.getINSTANCE().getUser().getFolders().get(getArguments().getInt("folderIndex")).getId();
        String toDoKey = Session.getINSTANCE().getUser().getFolders().get(getArguments()
                .getInt("folderIndex")).getTodos().get(position).getId();
        todoList.remove(position);
        adapter.notifyDataSetChanged();
        Session.getINSTANCE().getUser().getFolders().get(getArguments().getInt("folderIndex")).getTodos().remove(position);
        database.child("users").child(userKey).child("folders").child(folderKey).child("todos").child(toDoKey).removeValue();
        return true;
    }
}
