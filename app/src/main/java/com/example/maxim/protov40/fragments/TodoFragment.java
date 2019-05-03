package com.example.maxim.protov40.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maxim.protov40.R;

public class TodoFragment extends Fragment implements View.OnClickListener{
    private TextView name;
    private TextView disc;
    private TextView time;
    private Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_layout, container, false);
        name = (TextView) view.findViewById(R.id.text_name);
        disc = (TextView) view.findViewById(R.id.text_disc);
        time = (TextView) view.findViewById(R.id.text_time);
        button = (Button) view.findViewById(R.id.btn_back);
        name.setText(name.getText().toString() + " " + getArguments().getString("name"));
        disc.setText(disc.getText().toString() + " " + getArguments().getString("disc"));
        time.setText(time.getText().toString() + " " + getArguments().getString("time"));
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        TodosFragment todosFragment = new TodosFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, todosFragment);
        Bundle bundle = new Bundle();
        bundle.putInt("pos", getArguments().getInt("pos"));
        todosFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }
}
