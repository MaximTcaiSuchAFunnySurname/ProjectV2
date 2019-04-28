package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.util.Folder;
import com.example.maxim.protov40.util.ILogin;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.ToDo;
import com.example.maxim.protov40.util.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LogInFragment extends Fragment implements View.OnClickListener, ILogin {
    private EditText loginEdit, passwordEdit;
    private Button loginButton, signUpButton;
    String username, password;
    private FragmentTransaction trans;
    private DatabaseReference database;
    private List<User> userList;
    Bundle bundle;

    public LogInFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        loginEdit = (EditText) view.findViewById(R.id.login_edit);
        passwordEdit = (EditText) view.findViewById(R.id.password_edit);
        loginButton = (Button) view.findViewById(R.id.login_btn);
        signUpButton = (Button) view.findViewById(R.id.signup_btn);
        database = FirebaseDatabase.getInstance().getReference();
        username = "";
        password = "";
        userList = new ArrayList<>();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot elem : dataSnapshot.getChildren()
                        ) {
                    HashMap map = (HashMap) elem.getValue();
                    HashMap map2 = (HashMap) map.get(map.keySet().toArray()[0]);
                    User user = new User(map2.get("login").toString(), map2.get("password").toString(), (ArrayList<Folder>) map2.get("folders"));
                    userList.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        trans = getFragmentManager().beginTransaction();
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
             if (login(loginEdit.getText().toString(), passwordEdit.getText().toString())) {
                    Toast.makeText(getActivity(), "Congratulations, you have logged in", Toast.LENGTH_SHORT).show();
                    trans.replace(R.id.frame, new FoldersFragment());
                    trans.commit();
                } else {
                    Toast.makeText(getActivity(), "Error in login and/or password", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.signup_btn:
                username = loginEdit.getText().toString();
                password = passwordEdit.getText().toString();
                writeNewUser(username, password);
                loginEdit.setText("");
                passwordEdit.setText("");
                Toast.makeText(getActivity(), "Congratulations, you have signed up", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean login(String login, String password) {
        for (User elem:userList
             ) {
            if (elem.getLogin().equals(login) && elem.getPassword().equals(password))
                Session.getINSTANCE().setUser(elem);
                return true;
        }
        return false;
    }

    @Override
    public void writeNewUser(String login, String password) {
        ToDo todo = new ToDo("", "", "");
        Folder folder = new Folder("Test", Collections.singletonList(todo));
        User user = new User(login, password,Collections.singletonList(folder));
        String key = database.child("users").push().getKey();
        database.child("users").child(key).setValue(user);
    }
}
