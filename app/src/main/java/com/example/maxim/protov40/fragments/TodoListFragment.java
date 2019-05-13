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
import com.baoyz.swipemenulistview.*;

import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment implements View.OnClickListener, CreateTodoDialogFragment.ICreateTodo {
    private SwipeMenuListView listView;
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
        listView = (SwipeMenuListView) view.findViewById(R.id.notes_list);
        createTodo = (Button) view.findViewById(R.id.create_todo);
        back = (Button) view.findViewById(R.id.back_button1);
        todoList = new ArrayList<>();
        if(Session.getINSTANCE().getUser()
                .getFolders().get(Session.getINSTANCE().getFolderPosition()).getTodos() != null) {
            for (ToDo elem : Session.getINSTANCE().getUser()
                    .getFolders().get(Session.getINSTANCE().getFolderPosition()).getTodos()
                    ) {
                todoList.add(elem.getName());
            }
        }
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem open = new SwipeMenuItem(getActivity());
                SwipeMenuItem delete = new SwipeMenuItem(getActivity());
                open.setBackground(new ColorDrawable(Color.GREEN));
                open.setWidth(220);
                open.setIcon(R.drawable.todo_open);
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
                        Session.getINSTANCE().setTodoPosition(position);
                        TodoFragment fragment = new TodoFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame, fragment);
                        transaction.commit();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Deleting toDo...", Toast.LENGTH_SHORT).show();
                        String userKey = Session.getINSTANCE().getUser().getId();
                        String folderKey = Session.getINSTANCE().getUser().getFolders().get(Session.getINSTANCE().getFolderPosition()).getId();
                        String toDoKey = Session.getINSTANCE().getUser().getFolders()
                                .get(Session.getINSTANCE().getFolderPosition()).getTodos().get(position).getId();
                        todoList.remove(position);
                        adapter.notifyDataSetChanged();
                        Session.getINSTANCE().getUser().getFolders().get(Session.getINSTANCE().getFolderPosition()).getTodos().remove(position);
                        database.child("users").child(userKey).child("folders").child(folderKey).child("todos").child(toDoKey).removeValue();
                        break;
                }
                return true;
            }
        });
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, todoList);
        database = FirebaseDatabase.getInstance().getReference();
        listView.setAdapter(adapter);

        back.setOnClickListener(this);
        createTodo.setOnClickListener(this);
        return view;
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
                    , data.getText().toString()), Session.getINSTANCE().getFolderPosition());
            todoList.add(name.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
