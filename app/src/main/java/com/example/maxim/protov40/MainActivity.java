package com.example.maxim.protov40;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.maxim.protov40.fragments.FoldersFragment;
import com.example.maxim.protov40.fragments.LogInFragment;
import com.example.maxim.protov40.fragments.dialog.CreateFolderDialogFragments;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogInFragment loginF = new LogInFragment();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.frame, loginF);
        trans.commit();
    }
}
