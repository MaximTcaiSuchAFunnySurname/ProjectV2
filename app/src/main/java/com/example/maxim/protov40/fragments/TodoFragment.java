package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import me.biubiubiu.justifytext.library.*;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class TodoFragment extends Fragment implements View.OnClickListener {
    private TextView todoName;
    private JustifyTextView todoText;
    private TextView todoData;
    private Button back;

    public TodoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_layout, container, false);
        todoName = (TextView) view.findViewById(R.id.todo_name);
        todoText = (JustifyTextView) view.findViewById(R.id.todo_text);
        todoData = (TextView) view.findViewById(R.id.todo_data);
        back = (Button) view.findViewById(R.id.back_button0);
//        todoName.setText(getArguments().getString("name"));
//        todoText.setText(getArguments().getString("text"));
//        todoData.setText(getArguments().getString("data"));
        todoName.setText(getTodoInfo("name"));
        todoText.setText(getTodoInfo("text"));
        todoData.setText(getTodoInfo("data"));
        back.setOnClickListener(this);
        return view;
    }


    public String getTodoInfo(String key) {
        switch (key.toString()) {
            case "name":
                return Session.getINSTANCE().getUser().getFolders()
                        .get(Session.getINSTANCE().getFolderPosition()).getTodos()
                        .get(Session.getINSTANCE().getTodoPosition()).getName();
            case "text":
                return Session.getINSTANCE().getUser().getFolders()
                        .get(Session.getINSTANCE().getFolderPosition()).getTodos()
                        .get(Session.getINSTANCE().getTodoPosition()).getText();
            case "data":
                return Session.getINSTANCE().getUser().getFolders()
                        .get(Session.getINSTANCE().getFolderPosition()).getTodos()
                        .get(Session.getINSTANCE().getTodoPosition()).getData();
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        TodoListFragment fragment = new TodoListFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
