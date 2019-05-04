package com.example.maxim.protov40.fragments.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.fragments.FoldersFragment;
import com.example.maxim.protov40.util.Folder;
import com.example.maxim.protov40.util.Session;
import com.example.maxim.protov40.util.Storage;
import com.example.maxim.protov40.util.ToDo;

import java.util.Collections;

public class CreateFolderDialogFragments extends DialogFragment {
    FoldersFragment foldersFragment;

    public interface CreateFolderDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    CreateFolderDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListener = foldersFragment;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.create_folder_dialog, null))
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(CreateFolderDialogFragments.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(CreateFolderDialogFragments.this);
                    }
                });
        return builder.create();
    }

    public void setFoldersFragment(FoldersFragment foldersFragment) {
        this.foldersFragment = foldersFragment;
    }
}
