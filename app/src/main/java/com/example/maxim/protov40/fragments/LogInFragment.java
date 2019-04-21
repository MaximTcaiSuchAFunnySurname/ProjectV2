package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxim.protov40.R;

public class LogInFragment extends Fragment implements View.OnClickListener {
    private EditText loginEdit, passwordEdit;
    private Button loginButton, signUpButton;
    String username, password;

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
        username = "";
        password = "";
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                //TODO: Замена на FireBase
                if (username.equals(loginEdit.getText().toString())
                        && password.equals(passwordEdit.getText().toString()))
                    Toast.makeText(getActivity(), "Congratulations, you have logged in", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getActivity(), "Error in login and/or password", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.signup_btn:
                username = loginEdit.getText().toString();
                password = passwordEdit.getText().toString();
                loginEdit.setText("");
                passwordEdit.setText("");
                Toast.makeText(getActivity(), "Congratulations, you have signed up", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
