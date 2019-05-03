package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.util.Session;

import org.w3c.dom.Text;

public class TodoFragment extends Fragment {
    private TextView todoName;
    private TextView todoText;
    private TextView todoData;

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
        todoText = (TextView) view.findViewById(R.id.todo_text);
        todoData = (TextView) view.findViewById(R.id.todo_data);
        todoName.setText(getTodoInfo("name"));
        todoText.setText(getTodoInfo("text"));
        todoData.setText(getTodoInfo("data"));
        return view;
    }

    public String getTodoInfo(String key) {
        switch (key.toString()) {
            case "name":
                return Session.getINSTANCE().getUser().getFolders()
                        .get(getArguments().getInt("folderIndex")).getTodos()
                        .get(getArguments().getInt("todoIndex")).getName();
            case "text": return Session.getINSTANCE().getUser().getFolders()
                    .get(getArguments().getInt("folderIndex")).getTodos()
                    .get(getArguments().getInt("todoIndex")).getText();
            case "data":
                return Session.getINSTANCE().getUser().getFolders()
                        .get(getArguments().getInt("folderIndex")).getTodos()
                        .get(getArguments().getInt("todoIndex")).getData();
        }
        return "";
    }


}
