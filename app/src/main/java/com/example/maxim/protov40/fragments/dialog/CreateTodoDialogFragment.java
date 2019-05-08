package com.example.maxim.protov40.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.maxim.protov40.R;

public class CreateTodoDialogFragment extends DialogFragment {
    public interface ICreateTodo{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private ICreateTodo mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle(R.string.create_todo_title);
        builder.setView(inflater.inflate(R.layout.create_todo_dialog_layout, null))
                .setPositiveButton(R.string.create_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(CreateTodoDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(CreateTodoDialogFragment.this);
                    }
                });
        return builder.create();
    }

    public void setmListener(ICreateTodo mListener) {
        this.mListener = mListener;
    }
}
