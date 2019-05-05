package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.ToDo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TodoListFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView listView;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    private Button createTodo;
    private Button back;

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
        list = new ArrayList<>();
        for (ToDo elem : Session.getINSTANCE().getUser()
                .getFolders().get(getArguments().getInt("folderIndex")).getTodos()
                ) {
            list.add(elem.getName());
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        back.setOnClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Bundle bundle = new Bundle();
        bundle.putInt("todoIndex", position);
        TodoFragment fragment = new TodoFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        FoldersFragment fragment = new FoldersFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
